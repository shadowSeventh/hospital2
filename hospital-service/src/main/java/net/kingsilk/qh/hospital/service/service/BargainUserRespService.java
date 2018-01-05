package net.kingsilk.qh.hospital.service.service;

import com.google.common.collect.Lists;
import com.querydsl.core.types.dsl.Expressions;
import net.kingsilk.qh.bargain.api.bargainApp.bargain.wap.bargain.dto.BargainUserResp;
import net.kingsilk.qh.bargain.domain.QBargainHelpUser;
import net.kingsilk.qh.hospital.service.util.DbUtil;
import net.kingsilk.qh.oauth.api.user.UserGetResp;
import net.kingsilk.wx4j.broker.api.wxCom.mp.at.WxComMpAtApi;
import net.kingsilk.wx4j.client.mp.api.user.InfoResp;
import net.kingsilk.wx4j.client.mp.api.user.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Optional;

@Service
public class BargainUserRespService {

    @Autowired
    private WxComMpAtApi wxComMpAtApi;

    @Autowired
    private BargainHelpUserRepo bargainHelpUserRepo;

    @Autowired
    private UserApi userApi;

    @Autowired
    private net.kingsilk.qh.oauth.api.user.UserApi oauthUserApi;

    public BargainUserResp convert(BargainRecord bargainRecord, String wxComAppId, String wxMpAppId) {

        BargainUserResp bargainUserResp = new BargainUserResp();

        BargainHelpUser bestHelper = Lists.newLinkedList(bargainHelpUserRepo.findAll(
                Expressions.allOf(
                        QBargainHelpUser.bargainHelpUser.deleted.ne(true),
                        QBargainHelpUser.bargainHelpUser.userId.eq(bargainRecord.getUserId()),
                        QBargainHelpUser.bargainHelpUser.bargainId.eq(bargainRecord.getBargainId()),
                        DbUtil.opIn(QBargainHelpUser.bargainHelpUser.helpUserId, bargainRecord.getHelpUsers())
                )
        )).stream().max(Comparator.comparing(BargainHelpUser::getHelpPrice)).orElse(new BargainHelpUser());

        //获取本地accesstoken
        net.kingsilk.wx4j.broker.api.UniResp<net.kingsilk.wx4j.broker.api.wxCom.mp.at.GetResp> uniResp =
                wxComMpAtApi.get(wxComAppId, wxMpAppId);

        //获取微信头像
//        net.kingsilk.qh.oauth.api.UniResp<UserGetResp> resp = oauthUserApi.get(bestHelper.getHelpUserId());
        net.kingsilk.qh.oauth.api.UniResp<UserGetResp> resp = oauthUserApi.get(bargainRecord.getUserId());
        String openId = resp.getData().getWxUsers().stream().filter(wxUser ->
                wxMpAppId.equals(wxUser.getAppId())
        ).findFirst().orElse(new UserGetResp.WxUser()).getOpenId();
        if (StringUtils.hasText(openId)) {
            //查看用户是否关注微信公众号
            InfoResp infoResp = userApi.info(uniResp.getData().getAccessToken(), openId, "zh_CN");
            bargainUserResp.setAvatar(infoResp.getHeadImgUrl());
            bargainUserResp.setNickName(infoResp.getNickName());
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");

        Optional.ofNullable(bestHelper.getDateCreated()).ifPresent(it ->
                bargainUserResp.setHelpTime(simpleDateFormat.format(it))
        );

        bargainUserResp.setUserId(bargainRecord.getUserId());
        bargainUserResp.setPrice(bargainRecord.getFinalPrice());
        return bargainUserResp;
    }
}
