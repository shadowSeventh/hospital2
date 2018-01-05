package net.kingsilk.qh.hospital.service.service;

import net.kingsilk.qh.shop.api.UniResp;
import net.kingsilk.qh.shop.api.brandApp.shop.ShopApi;
import net.kingsilk.qh.shop.api.brandApp.shop.dto.ShopResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BargainAppService {

    @Autowired
    private ShopApi shopApi;

    @Autowired
    private BargainAppRepo bargainAppRepo;

    public String getBrandAppId(String bargainAppId) {

        BargainApp bargainApp = bargainAppRepo.findOne(bargainAppId);

        return bargainApp.getBrandAppId();
    }

    public String getShopName(String bargainAppId) {

        String shopName = getShopInfo(bargainAppId).getData().getName();

        return shopName;
    }

    public String getShopTel(String bargainAppId) {
        String tel = getShopInfo(bargainAppId).getData().getTel();
        return tel;
    }

    public UniResp<ShopResp> getShopInfo(String bargainAppId) {
        BargainApp bargainApp = bargainAppRepo.findOne(bargainAppId);

        String shopId = bargainApp.getShopId();
        UniResp<ShopResp> uniResp =
                shopApi.info(bargainApp.getBrandAppId(), shopId);


        return uniResp;
    }
}
