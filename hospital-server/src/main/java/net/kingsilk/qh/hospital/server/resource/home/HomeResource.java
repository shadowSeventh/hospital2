package net.kingsilk.qh.hospital.server.resource.home;

import com.querydsl.core.types.dsl.Expressions;
import net.kingsilk.qh.bargain.api.ErrStatus;
import net.kingsilk.qh.bargain.api.UniResp;
import net.kingsilk.qh.bargain.api.home.HomeApi;
import net.kingsilk.qh.bargain.domain.QBargainApp;
import net.kingsilk.qh.bargain.domain.Staff;
import net.kingsilk.qh.bargain.repo.StaffRepo;
import net.kingsilk.qh.hospital.service.service.BargainAppService;
import net.kingsilk.qh.oauth.api.user.UserApi;
import net.kingsilk.qh.oauth.api.user.UserGetResp;
import net.kingsilk.qh.shop.api.brandApp.shop.dto.ShopResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class HomeResource implements HomeApi {

    @Autowired
    private BargainAppRepo bargainAppRepo;

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private BargainAppService bargainAppService;

    @Autowired
    private UserApi userApi;

    @Override
    public UniResp<String> getBargainAppId(String brandAppId, String shopId) {
        UniResp<String> uniResp = new UniResp<>();

        if (StringUtils.isEmpty(brandAppId)) {
            uniResp.setStatus(ErrStatus.ACTIVITYERROR);
            uniResp.setData("参数不能为空");
            return uniResp;
        }
        if (StringUtils.isEmpty(shopId)) {
            uniResp.setStatus(ErrStatus.ACTIVITYERROR);
            uniResp.setData("参数不能为空");
            return uniResp;
        }
        //先去查找是否该门店是否有砍价活动记录
        BargainApp bargainApp = bargainAppRepo.findOne(
                Expressions.allOf(
                        QBargainApp.bargainApp.shopId.eq(shopId),
                        QBargainApp.bargainApp.brandAppId.eq(brandAppId)
                ));

        String bargainAppId = null;
        if (bargainApp == null) {
            bargainApp = new BargainApp();
            bargainApp.setShopId(shopId);
            bargainApp.setBrandAppId(brandAppId);
            bargainAppRepo.save(bargainApp);
            bargainAppId = bargainApp.getId();
            net.kingsilk.qh.shop.api.UniResp<ShopResp> resp =
                    bargainAppService.getShopInfo(bargainAppId);
            net.kingsilk.qh.oauth.api.UniResp<net.kingsilk.qh.oauth.api.UniPage<UserGetResp>> pageUniResp =
                    userApi.search(10, 0, null, resp.getData().getPhone());
            String userId = pageUniResp.getData().getContent().get(0).getId();
            //新建管理员 用于权限管理
            Staff staff = new Staff();
            staff.setUserId(userId);
            staff.setBargainAppId(bargainAppId);
            Set<String> stringSet = new LinkedHashSet<>();
            stringSet.add("SA");
            staff.setAuthorities(stringSet);
            staffRepo.save(staff);
        } else {
            bargainAppId = bargainApp.getId();
        }

        if (StringUtils.isEmpty(bargainAppId)) {
            uniResp.setStatus(ErrStatus.FOUNDNULL);
            uniResp.setData("bargainAppId不能为空");
            return uniResp;
        }
        uniResp.setData(bargainAppId);
        uniResp.setStatus(200);

        return uniResp;
    }
}
