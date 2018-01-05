package net.kingsilk.qh.hospital.service.service;

import net.kingsilk.qh.bargain.api.ErrStatus;
import net.kingsilk.qh.bargain.api.ErrStatusException;
import net.kingsilk.qh.hospital.service.HospitalProperties;
import net.kingsilk.qh.shop.api.UniResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private QhPayRepo qhPayRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderLogRepo orderLogRepo;

    @Autowired
    private BargainAppService bargainAppService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HospitalProperties porp;

    @Autowired
    private BargainRecordRepo bargainRecordRepo;

    /**
     * 支付成功处理
     *
     * @param order
     */
    public void paySuccessHandle(Order order, PayTypeEnum payType, Date payTime) {
        if (!OrderStatusEnum.UNPAYED.equals(order.getStatus())) {
            return;
        }

        String brandAppId = bargainAppService.getBrandAppId(order.getBargainAppId());

        QhPay qhPay = new QhPay();
        qhPay.setOrderId(order.getId());
        qhPay.setThirdPayAmount(order.getPaymentAmount());
        qhPay.setBrandAppId(brandAppId);
        qhPay.setRefundAmount(order.getPaymentAmount());
        qhPay.setPayType(payType);
        qhPay.setPayTime(payTime);
        qhPayRepo.save(qhPay);
//        BargainRecord record = bargainRecordRepo.findOne(
//                Expressions.allOf(
//                        QBargainRecord.bargainRecord.userId.eq(order.getUserId()),
//                        QBargainRecord.bargainRecord.bargainAwardId.eq(order.getBargainAwards().get(0)),
//                        QBargainRecord.bargainRecord.bargainId.eq(order.getBargainId()),
//                        QBargainRecord.bargainRecord.deleted.ne(true)
//                )
//        );
//        record.setStatus(BargainRecordStatusEnum.WAITGET);
//        bargainRecordRepo.save(record);
        order.setQhPayId(qhPay.getId());
        order.setStatus(OrderStatusEnum.UNCONFIRMED);
        orderRepo.save(order);


        //给门店账户打钱
//      /brandApp/{brandAppId}/shop/{shopId}/shopAccount/transfer
        String url = porp.getQhShop().getWap().getApi().getUrl() + "brandApp/" + brandAppId + "/shop/" + order.getShopId() + "/shopAccount/transfer";
        HttpHeaders headers = new HttpHeaders();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.ALL);
        headers.setAccept(mediaTypes);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("money", order.getPaymentAmount().toString());
        map.set("orderId", order.getId());
        String uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParams(map)
                .build()
                .toUri()
                .toString();

        HttpEntity<Map> reqEntity = new HttpEntity<>(null, headers);
        ResponseEntity<UniResp> respEntity = restTemplate.exchange(uri, HttpMethod.GET, reqEntity, UniResp.class);
        if (respEntity.getBody().getStatus() != 200) {
            OrderLog orderLog = new OrderLog();
            orderLog.setOrderId(order.getId());
            orderLog.setShopId(order.getShopId());
            orderLog.setBargainAppId(order.getBargainAppId());
            orderLog.setStatus(OrderStatusEnum.CANCELING);
            orderLog.setLastStatus(OrderStatusEnum.UNPAYED);
            orderLog.setMemo(order.getBuyerMemo());
            orderLogRepo.save(orderLog);
            throw new ErrStatusException(ErrStatus.FOUNDNULL, "转款失败");
        } else {
            //账户日志表进行保存
            OrderLog orderLog = new OrderLog();
            orderLog.setOrderId(order.getId());
            orderLog.setShopId(order.getShopId());
            orderLog.setBargainAppId(order.getBargainAppId());
            orderLog.setStatus(OrderStatusEnum.UNCONFIRMED);
            orderLog.setLastStatus(OrderStatusEnum.UNPAYED);
            orderLog.setMemo(order.getBuyerMemo());
            orderLogRepo.save(orderLog);
        }
    }

}
