package net.kingsilk.qh.hospital.service.service;

import com.google.common.collect.Lists;
import com.querydsl.core.types.dsl.Expressions;
import net.kingsilk.qh.bargain.api.ErrStatus;
import net.kingsilk.qh.bargain.api.ErrStatusException;
import net.kingsilk.qh.bargain.api.bargainApp.bargain.wap.bargain.dto.BargainResp;
import net.kingsilk.qh.bargain.domain.*;
import net.kingsilk.qh.hospital.service.HospitalProperties;
import net.kingsilk.qh.oauth.api.user.UserGetResp;
import net.kingsilk.qh.platform.api.brandApp.BrandAppApi;
import net.kingsilk.qh.platform.api.brandApp.dto.BrandAppResp;
import net.kingsilk.wx4j.broker.api.UniResp;
import net.kingsilk.wx4j.broker.api.wxCom.mp.at.WxComMpAtApi;
import net.kingsilk.wx4j.broker.api.wxCom.mp.scence.ScenceInfoReq;
import net.kingsilk.wx4j.broker.api.wxCom.mp.scence.WxScenceApi;
import net.kingsilk.wx4j.broker.api.wxCom.mp.user.GetResp;
import net.kingsilk.wx4j.broker.api.wxCom.mp.user.WxComMpUserApi;
import net.kingsilk.wx4j.client.mp.api.kfMsg.KfMsgApi;
import net.kingsilk.wx4j.client.mp.api.kfMsg.TextMsgReq;
import net.kingsilk.wx4j.client.mp.api.qrCode.CreateTicketResp;
import net.kingsilk.wx4j.client.mp.api.qrCode.QrCodeApi;
import net.kingsilk.wx4j.client.mp.api.qrCode.TmpReq;
import net.kingsilk.wx4j.client.mp.api.tplMsg.TplMsgApi;
import net.kingsilk.wx4j.client.mp.api.user.InfoResp;
import net.kingsilk.wx4j.client.mp.api.user.UserApi;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BargainRecordService {

    @Autowired
    private SecService secService;

    @Autowired
    private BrandAppApi brandAppApi;

    @Autowired
    private UserApi userApi;

    @Autowired
    private BargainHelpUserRepo bargainHelpUserRepo;

    @Autowired
    private BargainRecordRepo bargainRecordRepo;

    @Autowired
    private BargainAwardRepo bargainAwardRepo;

    @Autowired
    private net.kingsilk.qh.oauth.api.user.UserApi oauthUserApi;

    @Autowired
    private QrCodeApi qrCodeApi;

    @Autowired
    private WxComMpAtApi wxComMpAtApi;

    @Autowired
    private TplMsgApi tplMsgApi;

    @Autowired
    private KfMsgApi kfMsgApi;

    @Autowired
    private WxComMpUserApi wxComMpUserApi;

    @Autowired
    private HospitalProperties qhActivityProperties;

    @Autowired
    private BargainAppService bargainAppService;

    @Autowired
    private WxScenceApi wxScenceApi;

    @Autowired
    private BargainActivityRepo bargainActivityRepo;

    public Map<String, String> forceFollow(String bargainAppId, String activityId, String userId, String curUserId, String skuId, String shareUrl) {

        Map<String, String> map = new LinkedHashMap<>();
        String brandAppId = bargainAppService.getBrandAppId(bargainAppId);
        //获取微信相关id
        net.kingsilk.qh.platform.api.UniResp<BrandAppResp> respUniResp = brandAppApi.info(brandAppId);

        String wxComAppId = respUniResp.getData().getWxComAppId();
        String wxMpAppId = respUniResp.getData().getWxMpId();

        //获取本地accesstoken
        net.kingsilk.wx4j.broker.api.UniResp<net.kingsilk.wx4j.broker.api.wxCom.mp.at.GetResp> uniResp =
                wxComMpAtApi.get(wxComAppId, wxMpAppId);

        //获取微信头像
        net.kingsilk.qh.oauth.api.UniResp<UserGetResp> resp = oauthUserApi.get(userId);
        List<UserGetResp.WxUser> openIdList = resp.getData().getWxUsers().stream().filter(wxUser ->
                wxMpAppId.equals(wxUser.getAppId())
        ).collect(Collectors.toList());

        if (openIdList.isEmpty()) {
            map.put("int", String.valueOf(ErrStatus.WXUSER_404));
            return map;
        }
        String openId = openIdList.get(0).getOpenId();

        //查看用户是否关注微信公众号
        InfoResp infoResp = userApi.info(uniResp.getData().getAccessToken(), openIdList.get(0).getOpenId(), "zh_CN");

        map.put("int", String.valueOf(infoResp.getSubscribe()));
        if (infoResp.getSubscribe() == 0) {
            String url = qrCodeUrl(uniResp.getData().getAccessToken(), wxComAppId,
                    wxMpAppId, brandAppId, activityId, userId, curUserId, skuId, openId, shareUrl);
            map.put("url", url);
        }
        return map;
    }

    public String qrCodeUrl(
            String accessToken, String wxComAppId, String wxMpAppId, String brandAppId,
            String activityId, String userId, String curUserId, String skuId, String openId, String shareUrl) {

        TmpReq tmpReq = new TmpReq();
        TmpReq.ActionInfo.Scene scene = new TmpReq.ActionInfo.Scene();

        ScenceInfoReq scenceInfoReq = new ScenceInfoReq();
        scenceInfoReq.setExpiredAt("300");
        Map<String, String> senceData = new LinkedHashMap<>();

        //TODO
        senceData.put("brandAppId", brandAppId);
        senceData.put("userId", userId);
        senceData.put("bargainId", activityId);
        senceData.put("curUserId", curUserId);
        senceData.put("skuId", skuId);
        senceData.put("shareUrl", shareUrl);
        senceData.put("openId", openId);
        scenceInfoReq.setSenceData(senceData);
        UniResp<Integer> uniResp = wxScenceApi.save(wxComAppId, wxMpAppId, scenceInfoReq);
        scene.setScene_id(uniResp.getData());

        //调取微信的接口，创建一条场景id的记录 data里面放置workid,activityId
        TmpReq.ActionInfo actionInfo = new TmpReq.ActionInfo();
        actionInfo.setScene(scene);
        tmpReq.setAction_info(actionInfo);
        tmpReq.setExpire_seconds(300);
        CreateTicketResp createTicketResp = qrCodeApi.createTicket(accessToken, tmpReq);
        return createTicketResp.getTicket();
    }

    public net.kingsilk.qh.bargain.api.UniResp<BargainResp> bargain(String bargainAppId, String wxComAppId, String wxMpAppId, String activityId, String userId, String curUserId, String skuId, String shareUrl) {

        net.kingsilk.qh.bargain.api.UniResp<BargainResp> uniResp = new net.kingsilk.qh.bargain.api.UniResp<>();
        BargainResp bargainResp = new BargainResp();

        BargainRecord record = bargainRecordRepo.findOne(
                Expressions.allOf(
                        QBargainRecord.bargainRecord.userId.eq(userId),
                        QBargainRecord.bargainRecord.bargainAwardId.eq(skuId),
                        QBargainRecord.bargainRecord.bargainId.eq(activityId),
                        QBargainRecord.bargainRecord.deleted.ne(true)
                )
        );

        BargainAward award = bargainAwardRepo.findOne(skuId);

        if (record == null) {
            record = new BargainRecord();
            record.setBargainId(activityId);
            record.setBargainAppId(bargainAppId);
            record.setUserId(userId);
            record.setBargainAwardId(skuId);
            record.setFinalPrice(award.getPrice());
            System.out.println("1");
        } else {

            if (record.getUserId().equals(curUserId)) {
                bargainResp.setNewHelper(false);
                uniResp.setStatus(10089);
                uniResp.setData(bargainResp);
                return uniResp;
            }

            List<String> helpUsers = record.getHelpUsers();
            if (!helpUsers.isEmpty() && helpUsers.contains(curUserId)) {
                bargainResp.setNewHelper(false);
                uniResp.setStatus(10089);
                uniResp.setData(bargainResp);
                return uniResp;
            } else if (record.getFinalPrice().equals(award.getMinTargetPrice())) {
                bargainResp.setNewHelper(false);
                uniResp.setStatus(10102);
                uniResp.setData(bargainResp);
                return uniResp;
            }
        }

        net.kingsilk.qh.oauth.api.UniResp<UserGetResp> curUserInfo = oauthUserApi.get(curUserId);
        List<UserGetResp.WxUser> openIdList = curUserInfo.getData().getWxUsers().stream().filter(wxUser ->
                wxMpAppId.equals(wxUser.getAppId())
        ).collect(Collectors.toList());

        if (openIdList.isEmpty()) {
            throw new ErrStatusException(ErrStatus.WXUSER_404, "请重新登录");
        }

        String openId = openIdList.get(0).getOpenId();

        net.kingsilk.qh.oauth.api.UniResp<UserGetResp> userInfo = oauthUserApi.get(userId);
        List<UserGetResp.WxUser> openIds = userInfo.getData().getWxUsers().stream().filter(wxUser ->
                wxMpAppId.equals(wxUser.getAppId())
        ).collect(Collectors.toList());

        if (openIdList.isEmpty()) {
            throw new ErrStatusException(ErrStatus.WXUSER_404, "请重新登录");
        }

        String openIdUser = openIds.get(0).getOpenId();

        net.kingsilk.wx4j.broker.api.UniResp<GetResp> wxResp
                = wxComMpUserApi.get(wxComAppId, wxMpAppId, openId);
        if (wxResp.getStatus() == 10001) {
            throw new ErrStatusException(ErrStatus.WXUSER_404, "请重新登录");
        }

        if (award.getNum() <= 0) {
            bargainResp.setNewHelper(false);
            uniResp.setStatus(10101);
            uniResp.setData(bargainResp);
            return uniResp;
        }

        Boolean isSameUser = true;
        if (!curUserId.equals(userId)) {
            isSameUser = false;
        }
        Integer helpPrice;
        BargainActivity bargain = bargainActivityRepo.findOne(
                Expressions.allOf(
                        QBargainActivity.bargainActivity.id.eq(activityId),
                        QBargainActivity.bargainActivity.deleted.ne(true)
                )
        );

        List<BargainHelpUser> helpUsers = Lists.newArrayList(bargainHelpUserRepo.findAll(
                Expressions.allOf(
                        QBargainHelpUser.bargainHelpUser.bargainId.eq(activityId),
                        QBargainHelpUser.bargainHelpUser.deleted.ne(true),
                        QBargainHelpUser.bargainHelpUser.helpUserId.eq(curUserId)
                )

        ));

        BargainUserTypeEnum isNewUser;
        if (helpUsers.size() <= 0 || isSameUser) {
            isNewUser = BargainUserTypeEnum.NEW_USER;
        } else {
            isNewUser = BargainUserTypeEnum.OLD_USER;
        }

        BargainActivity.UserTypeRandom userTypeRandom = bargain.getUserTypeRandom().stream()
                .filter(it ->
                        isNewUser.equals(it.getUserType())
                )
                .findFirst().orElse(new BargainActivity.UserTypeRandom());

        Random random = new Random();
        Integer max = userTypeRandom.getHigherPrice();
        Integer min = userTypeRandom.getLowerPrice();
        int i = record.getFinalPrice() - award.getMinTargetPrice();
        if (i >= max) {
            helpPrice = Math.abs(random.nextInt(max)) % (max - min + 1) + min;
            if (helpPrice.equals(i)) {
                if (BargainReceiveTypeEnum.LINEBUY.equals(bargain.getReceiveType())) {
                    record.setStatus(BargainRecordStatusEnum.WAITGET);
                } else {
                    record.setStatus(BargainRecordStatusEnum.FINISH);
                }
                if (!BargainReceiveTypeEnum.ONLINEBUY.equals(bargain.getReceiveType())) {
                    award.setNum(award.getNum() - 1);
                    bargainAwardRepo.save(award);
                }
            }
        } else {
            if (i > 0) {
                helpPrice = i;
            } else {
                helpPrice = 0;
            }
            if (BargainReceiveTypeEnum.LINEBUY.equals(bargain.getReceiveType())) {
                record.setStatus(BargainRecordStatusEnum.WAITGET);
            } else {
                record.setStatus(BargainRecordStatusEnum.FINISH);
            }
            if (!BargainReceiveTypeEnum.ONLINEBUY.equals(bargain.getReceiveType())) {
                award.setNum(award.getNum() - 1);
                bargainAwardRepo.save(award);
            }
        }
        record.setFinalPrice(record.getFinalPrice() - helpPrice);
        BargainHelpUser helpUser = new BargainHelpUser();
//        String ip = commonService.getClientIpAddress(request)
//        helpUser.ip = ip
        helpUser.setUserId(userId);
        //获取本地accesstoken
        net.kingsilk.wx4j.broker.api.UniResp<net.kingsilk.wx4j.broker.api.wxCom.mp.at.GetResp> uniRespWx =
                wxComMpAtApi.get(wxComAppId, wxMpAppId);

        //获取微信头像
        InfoResp infoResp = userApi.info(uniRespWx.getData().getAccessToken(), openId, "zh_CN");
        InfoResp infoRespUser = userApi.info(uniRespWx.getData().getAccessToken(), openIdUser, "zh_CN");

        helpUser.setHelpUserImag(infoResp.getHeadImgUrl());
//        bargainUserResp.setHelpTime();
        helpUser.setHelpUserName(infoResp.getNickName());
        helpUser.setHelpUserId(curUserId);
        helpUser.setHelpPrice(helpPrice);
        helpUser.setBargainId(bargain.getId());
//        helpUser.setBargainRecordId(record.getId());
        record.getHelpUsers().add(helpUser.getHelpUserId());

        bargainHelpUserRepo.save(helpUser);

        bargainRecordRepo.save(record);
//        Bargain bargain = bargainRecord.bargain
        bargain.setParticipantNum(Optional.ofNullable(bargain.getParticipantNum()).orElse(0) + 1);
        bargainActivityRepo.save(bargain);
        shareUrl = "https:" + shareUrl;
        if (isSameUser) {
            sendMsgUser(wxComAppId, wxMpAppId, openId, shareUrl, String.valueOf(helpPrice));
        } else {
            sendMsgHelper(wxComAppId, wxMpAppId, openId, shareUrl, infoRespUser.getNickName(), String.valueOf(helpPrice));
        }

        bargainResp.setNewHelper(true);
        bargainResp.setBargainRecordId(record.getId());
        bargainResp.setCurrentPrice(record.getFinalPrice());
        bargainResp.setHelpCount(record.getHelpUsers().size());
        bargainResp.setHelpPrice(helpPrice);
        bargainResp.setSameUser(isSameUser);

        uniResp.setStatus(HttpStatus.SC_OK);
        uniResp.setData(bargainResp);
        return uniResp;
    }

    public void sendMsgHelper(String wxComAppId, String wxMpAppId, String openId, String shareUrl, String niceName, String money) {
//        net.kingsilk.wx4j.broker.api.UniResp<net.kingsilk.wx4j.broker.api.wxCom.mp.at.GetResp> respUniResp =
//                wxComMpAtApi.get(wxComAppId, wxMpAppId);
//        SendReq sendReq = new SendReq();
//        sendReq.setTouser(openId);
//        sendReq.setUrl(shareUrl);
//        sendReq.setTemplate_id(qhActivityProperties.getWx4jUt().getTplId());
//        Map<String, SendReq.Val> data = new LinkedHashMap<>();
//        SendReq.Val first = new SendReq.Val();
//        StringBuilder sb = new StringBuilder("恭喜您成功砍掉");
//        sb.append(money).append("元！");
//        first.setValue(sb.toString());
//        first.setColor("#743A3A");
//        SendReq.Val keyword1 = new SendReq.Val();
//        keyword1.setValue("<a href='" + shareUrl + "'>找人砍价</a>");
//        keyword1.setColor("#743A3A");
//        SendReq.Val keyword2 = new SendReq.Val();
//        keyword2.setValue("");
//        keyword2.setColor("#743A3A");
//        SendReq.Val remark = new SendReq.Val();
//        remark.setValue("");
//        remark.setColor("#743A3A");
//        data.put("first", first);
//        data.put("keyword1", keyword1);
//        data.put("keyword2", keyword2);
//        data.put("remark", remark);
//        sendReq.setData(data);
//        tplMsgApi.send(respUniResp.getData().getAccessToken(), sendReq);
        net.kingsilk.wx4j.broker.api.UniResp<net.kingsilk.wx4j.broker.api.wxCom.mp.at.GetResp> respUniResp =
                wxComMpAtApi.get(wxComAppId, wxMpAppId);
        TextMsgReq req = new TextMsgReq();
        req.setMsgType("text");
        req.setToUser(openId);
        TextMsgReq.Text text = new TextMsgReq.Text();
        String price = BigDecimal.valueOf(Long.valueOf(money)).divide(new BigDecimal(100)).toString();
        text.setContent("恭喜您成功帮" + "\"" + niceName + "\"砍掉" + price + "元\n" +
                "<a href='" + shareUrl + "'>我要参加</a>");
        req.setText(text);
        kfMsgApi.send(respUniResp.getData().getAccessToken(), req);

    }

    public void sendMsgUser(String wxComAppId, String wxMpAppId, String openId, String shareUrl, String money) {
//        net.kingsilk.wx4j.broker.api.UniResp<net.kingsilk.wx4j.broker.api.wxCom.mp.at.GetResp> respUniResp =
//                wxComMpAtApi.get(wxComAppId, wxMpAppId);
//        SendReq sendReq = new SendReq();
//        sendReq.setTouser(openId);
//        sendReq.setUrl(shareUrl);
//        sendReq.setTemplate_id(qhActivityProperties.getWx4jUt().getTplId());
//        Map<String, SendReq.Val> data = new LinkedHashMap<>();
//        SendReq.Val first = new SendReq.Val();
//        StringBuilder sb = new StringBuilder("恭喜您成功帮他砍掉");
//        sb.append(money).append("元！");
//        first.setValue(sb.toString());
//        first.setColor("#743A3A");
//        SendReq.Val keyword1 = new SendReq.Val();
//        keyword1.setValue("<a href='" + shareUrl + "'>我要参加</a>");
//        keyword1.setColor("#743A3A");
//        SendReq.Val keyword2 = new SendReq.Val();
//        keyword2.setValue(new Date() + "~" + new Date());
//        keyword2.setColor("#743A3A");
//        SendReq.Val remark = new SendReq.Val();
//        remark.setValue("投票将在" + new Date() + "开始," + new Date() + "结束,记得去分享拉票哦！");
//        remark.setColor("#743A3A");
//        data.put("first", first);
//        data.put("keyword1", keyword1);
//        data.put("keyword2", keyword2);
//        data.put("remark", remark);
//        sendReq.setData(data);
//        tplMsgApi.send(respUniResp.getData().getAccessToken(), sendReq);

        net.kingsilk.wx4j.broker.api.UniResp<net.kingsilk.wx4j.broker.api.wxCom.mp.at.GetResp> respUniResp =
                wxComMpAtApi.get(wxComAppId, wxMpAppId);
        TextMsgReq req = new TextMsgReq();
        req.setMsgType("text");
        req.setToUser(openId);
        TextMsgReq.Text text = new TextMsgReq.Text();
        String price = BigDecimal.valueOf(Integer.valueOf(money)).divide(new BigDecimal(100)).toString();
        text.setContent("恭喜您成功砍掉" + price + "元\n" +
                "<a href='" + shareUrl + "'>找人砍价</a>");
        req.setText(text);
        kfMsgApi.send(respUniResp.getData().getAccessToken(), req);

    }
}
