package net.kingsilk.qh.hospital.server.resource.bargainApp.authorities;


import net.kingsilk.qh.bargain.api.UniResp;
import net.kingsilk.qh.bargain.api.bargainApp.authorities.AuthoritiesApi;
import net.kingsilk.qh.bargain.domain.Staff;
import net.kingsilk.qh.bargain.repo.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        staff.setUserId(userId);
        Set<String> set = new LinkedHashSet<>();
        set.add("SA");
        staff.setAuthorities(set);
        staff.setBargainAppId(bargainAppId);
        staffRepo.save(staff);
        UniResp<String> uniResp = new UniResp<>();
        uniResp.setStatus(200);
        uniResp.setData(staff.getId());
        return uniResp;
    }
}
