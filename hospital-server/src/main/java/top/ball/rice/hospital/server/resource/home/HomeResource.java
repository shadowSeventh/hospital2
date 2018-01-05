package top.ball.rice.hospital.server.resource.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import top.ball.rice.hospital.api.ErrStatus;
import top.ball.rice.hospital.api.UniResp;
import top.ball.rice.hospital.api.home.HomeApi;
import top.ball.rice.hospital.repo.StaffRepo;

@Component
public class HomeResource implements HomeApi {


    @Autowired
    private StaffRepo staffRepo;

//    @Autowired
//    private BargainAppService bargainAppService;


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


        uniResp.setStatus(200);

        return uniResp;
    }
}
