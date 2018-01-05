package net.kingsilk.qh.hospital.server.resource.bargainApp.common;

import net.kingsilk.qh.bargain.api.UniResp;
import net.kingsilk.qh.bargain.api.common.CommonApi;
import net.kingsilk.qh.hospital.service.service.AddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static net.kingsilk.qh.hospital.service.service.AddrService.getAddr;

@Component("commonAddrResource")
public class CommonResource implements CommonApi {

    @Autowired
    private AddrService addrService;

    @Override
    public UniResp<List<Map<String, Object>>> getAdcList() {
        List<Map<String, Object>> resp;
        if (getAddr != null && getAddr.size() > 0) {
            resp = getAddr;
        } else {
            resp = addrService.getAddrList();
            getAddr = resp;
        }
        UniResp<List<Map<String, Object>>> uniResp = new UniResp<>();
        uniResp.setData(resp);
        uniResp.setStatus(200);
        return uniResp;
    }

}
