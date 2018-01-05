package net.kingsilk.qh.hospital.server.resource.bargainApp.staff;

import net.kingsilk.qh.bargain.api.UniPage;
import net.kingsilk.qh.bargain.api.UniResp;
import net.kingsilk.qh.bargain.api.bargainApp.staff.StaffApi;
import net.kingsilk.qh.bargain.api.bargainApp.staff.dto.StaffInfoResp;
import net.kingsilk.qh.bargain.api.bargainApp.staff.dto.StaffMinInfo;
import net.kingsilk.qh.bargain.api.bargainApp.staff.dto.StaffSaveReq;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StaffResource implements StaffApi {
    @Override
    public UniResp<StaffInfoResp> info(String bargainAppId, String id) {
        return null;
    }

    @Override
    public UniResp<String> save(String bargainAppId, StaffSaveReq staffSaveReq) {
        return null;
    }

    @Override
    public UniResp<String> update(String bargainAppId, String id, StaffSaveReq staffSaveReq) {
        return null;
    }

    @Override
    public UniResp<UniPage<StaffMinInfo>> page(String bargainAppId, int size, int page, List<String> sort, String keyWord) {
        return null;
    }

    @Override
    public UniResp<String> enable(String bargainAppId, String id, boolean status) {
        return null;
    }
}
