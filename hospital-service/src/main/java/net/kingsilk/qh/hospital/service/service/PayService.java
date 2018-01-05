package net.kingsilk.qh.hospital.service.service;

import net.kingsilk.qh.bargain.api.ErrStatusException;
import net.kingsilk.qh.bargain.api.UniResp;
import net.kingsilk.qh.hospital.service.HospitalProperties;
import net.kingsilk.qh.shop.api.ErrStatus;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class PayService {

    @Autowired
    private HospitalProperties prop;

    @Autowired
    @Qualifier("oauthRestTemplate")
    private RestTemplate restTemplate;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CommonService commonService;

    @Autowired
    private UserService userService;

    @Autowired
    private BargainAppService bargainAppService;

    //支付
    public UniResp<String> pay(Order order, HttpServletRequest request) {
        String ip = request.getRemoteAddr();

        String brandAppId = bargainAppService.getBrandAppId(order.getBargainAppId());

        String appId = userService.getAppId(brandAppId);
        if (order.getStatus() != OrderStatusEnum.UNPAYED && order.getStatus() != OrderStatusEnum.UNCOMMITED) {
            throw new ErrStatusException(ErrStatus.FINDNULL, "订单状态错误");
        }
        Map oauthUser = userService.getOauthUserInfo(request);

        StringBuilder openId = new StringBuilder();
        ArrayList<LinkedHashMap<String, String>> wxUserList = (ArrayList<LinkedHashMap<String, String>>) oauthUser.get("wxUsers");
        Optional.ofNullable(wxUserList).ifPresent(wxUsers ->

                wxUsers.stream().filter(wxUser ->
                        appId.equals(wxUser.get("appId"))
                ).findFirst().ifPresent(it ->
                        openId.append(it.get("openId"))
                )

        );

        if (StringUtils.isBlank(openId)) {
            throw new ErrStatusException(ErrStatus.PARTNER_401, "openId为空");
        }

        StringBuffer sb = new StringBuffer();
        //TODO
//        Optional.ofNullable(name).ifPresent(it ->
//                sb.append(it).append("-")
//        );
        sb.append("砍价活动").append("-").append(order.getSeq());
        String phone = (String) oauthUser.get("phone");
        Map<String, String> map = new LinkedHashMap();
        map.put("totalFee", String.valueOf(order.getPaymentAmount()));
        map.put("outTradeNo", order.getId());
        map.put("openId", openId.toString());
        map.put("bizMemo", Optional.ofNullable(order.getBuyerMemo()).orElse(order.getSeq()));
        map.put("itemTitle", sb.toString());
        map.put("itemDetail", order.getSeq());
        map.put("spBillCreateIp", ip);
        map.put("notifyUrl", prop.getQhBargain().getServer().getUrl() + "bargainApp/" + order.getBargainAppId() + "/bargain/" + order.getBargainId() + "/notify");

        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> type = new ArrayList<>();
        type.add(MediaType.APPLICATION_JSON);
        reqHeaders.setAccept(type);

        HttpEntity<Map> reqEntity = new HttpEntity<>(map, reqHeaders);
        String url = prop.getQhPay().getWap().getApi().getUrl() + brandAppId + "/trade";
        UniResp<Map> payMap;
        try {
            payMap = restTemplate.postForObject(url, reqEntity, UniResp.class);
        } catch (Exception e) {
            throw new ErrStatusException(ErrStatus.HTTPERR, "支付失败，请稍后再试");
        }
        System.out.println("--------------------支付系统返回值\n${payMap}");
        Map<String, String> data = (Map) payMap.getData();
        String resp = data.get("tradeId");
        UniResp<String> uniResp = new UniResp<>();
        uniResp.setStatus(HttpStatus.SC_OK);
        uniResp.setData(resp);
        return uniResp;
    }

}
