package top.ball.rice.hospital.server.resource.bargainApp.authorities;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ball.rice.hospital.api.UniResp;
import top.ball.rice.hospital.api.bargainApp.authorities.AuthoritiesApi;
import top.ball.rice.hospital.domain.Staff;
import top.ball.rice.hospital.repo.StaffRepo;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class AuthoritiesResource implements AuthoritiesApi {

    @Autowired
    private StaffRepo staffRepo;

    @Override
    public UniResp<Set<String>> getAuthorities(String bargainAppId) {
        return null;
    }

    @Override
    public UniResp<String> setSAStaff(String bargainAppId, String userId, String orgId) {
        Staff staff = new Staff();
        Set<String> set = new LinkedHashSet<>();
        set.add("SA");
        staff.setAuthorities(set);
//        staffRepo.save(staff);
        UniResp<String> uniResp = new UniResp<>();
        uniResp.setStatus(200);
        uniResp.setData(staff.getId());
        return uniResp;
    }
}
