package top.ball.rice.hospital.server.resource.bargainApp.common;

import org.springframework.stereotype.Component;
import top.ball.rice.hospital.api.UniResp;
import top.ball.rice.hospital.api.common.CommonApi;

import java.util.List;
import java.util.Map;

@Component("commonAddrResource")
public class CommonResource implements CommonApi {

//    @Autowired
//    private AddrService addrService;

    @Override
    public UniResp<List<Map<String, Object>>> getAdcList() {
//        List<Map<String, Object>> resp;
//        if (getAddr != null && getAddr.size() > 0) {
//            resp = getAddr;
//        } else {
//            resp = addrService.getAddrList();
//            getAddr = resp;
//        }
        UniResp<List<Map<String, Object>>> uniResp = new UniResp<>();
//        uniResp.setData(resp);
        uniResp.setStatus(200);
        return uniResp;
    }

}
