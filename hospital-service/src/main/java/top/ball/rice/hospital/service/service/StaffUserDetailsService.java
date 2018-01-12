//package top.ball.rice.hospital.service.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import top.ball.rice.hospital.domain.Staff;
//import top.ball.rice.hospital.repo.StaffRepo;
//import top.ball.rice.hospital.service.security.BargainAppIdFilter;
//
//@Service
//public class StaffUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private StaffRepo staffRepo;
//
////    @Autowired
////    private StaffAuthorityService staffAuthorityService;
//
//    public StaffUserDetailsService(StaffRepo staffRepo) {
//        this.staffRepo = staffRepo;
////        this.staffAuthorityService = staffAuthorityService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
////        List<GrantedAuthority> authorityList = new ArrayList<>();
//
//        Staff staff;
//
//        BargainAppIdFilter bargainAppIdFilter = new BargainAppIdFilter();
//        String bargainAppId = bargainAppIdFilter.getBargainAppId();
//
////        staff = staffRepo.findOne(allOf(
////                QStaff.staff.userId.eq(userId),
////                QStaff.staff.bargainAppId.eq(bargainAppId),
////                QStaff.staff.deleted.ne(true)
////        ));
//
//
////        if (staff != null) {
////            authorityList.add("SA");
////        }
////            Set<String> authoritySet = staffAuthorityService.getAuthorities(staff);
////
////            //TODO  权限按分配的来
////
////            if (!authoritySet.contains(StaffAuthorityEnum.STAFF.name())) {
////                authoritySet.add(StaffAuthorityEnum.STAFF.name());
////            }
////            if (staff.getAuthorities().contains("SA")) {
////                staffAuthorityService.fillAuth(authoritySet, StaffAuthorityEnum.SA);
////            }
////            List<GrantedAuthority> list =
////                    AuthorityUtils.createAuthorityList(authoritySet.toArray(new String[]{}));
////            authorityList.addAll(list);
////        }
//        User.UserBuilder userBuilder = User.withUsername(userId);
//        userBuilder.password("N/A");
//
////        if (staff != null) {
////            userBuilder.authorities("SA");
////        } else {
////            userBuilder.authorities("user");
////        }
//        return userBuilder.build();
//    }
//}
