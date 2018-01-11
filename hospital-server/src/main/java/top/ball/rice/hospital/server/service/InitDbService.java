package top.ball.rice.hospital.server.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import top.ball.rice.hospital.domain.Staff;
import top.ball.rice.hospital.repo.StaffRepo;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class InitDbService {
    @Autowired
    private StaffRepo staffRepo;

//    @Autowired
//    private BargainAppRepo bargainAppRepo;

//    @EventListener
    void onApplicationStated(ContextRefreshedEvent event) {
        initAdminUser();
    }

    private void initAdminUser() {
        Staff user = staffRepo.findOne(
                "58de6b27785a82000005a140"
        );
        if (user == null) {
            user = new Staff();
            user.setMemo("admin");
            user.setDisabled(true);
            Set<String> stringSet = new LinkedHashSet<>();
            stringSet.add("SA");
//            user.setAuthorities(stringSet);
            staffRepo.save(user);
        }

//        BargainApp bargainApp = new BargainApp();
//        bargainApp.setBrandAppId("5a3488e846e0fb00083bd03e");
//        bargainApp.setShopId("5a348951e828860007d787e0");
//        bargainApp.setUserId("5a0e87f552faff0008491879");
//        bargainAppRepo.save(bargainApp);
    }
}
