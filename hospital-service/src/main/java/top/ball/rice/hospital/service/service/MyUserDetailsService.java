package top.ball.rice.hospital.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.ball.rice.hospital.core.AuthorityEnum;
import top.ball.rice.hospital.domain.*;
import top.ball.rice.hospital.repo.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Context
    private HttpServletRequest request;

    @Autowired
    private SuffererRepo suffererRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private NurseRepo nurseRepo;

    @Autowired
    private StaffRepo staffRepo;

    public MyUserDetailsService() {

    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(userName);

        List<GrantedAuthority> authorityList = new ArrayList<>();


        Sufferer sufferer=suffererRepo.findOne(
                QSufferer.sufferer.userId.eq(user.getId())
        );
        Set<String> authoritySet = new HashSet<>();
        if (sufferer!=null){
            if (!authoritySet.contains(AuthorityEnum.SUFFERER.name())) {
                authoritySet.add(AuthorityEnum.SUFFERER.name());
            }
        }

        Doctor doctor=doctorRepo.findOne(
                QDoctor.doctor.userId.eq(userName)
        );

        if (doctor!=null){
            if (!authoritySet.contains(AuthorityEnum.DOCTOR.name())) {
                authoritySet.add(AuthorityEnum.DOCTOR.name());
            }
        }

        Nurse nurse=nurseRepo.findOne(
                QNurse.nurse.userId.eq(userName)
        );

        if (nurse!=null){
            if (!authoritySet.contains(AuthorityEnum.NURSE.name())) {
                authoritySet.add(AuthorityEnum.NURSE.name());
            }
        }

        Staff staff=staffRepo.findOne(
                QStaff.staff.userId.eq(userName)
        );

        if (staff!=null){
            if (!authoritySet.contains(AuthorityEnum.STAFF.name())) {
                authoritySet.add(AuthorityEnum.STAFF.name());
            }
        }

        //        if (staff != null) {
//            authorityList.add("SA");
//        }
//
//
//            //TODO  权限按分配的来
//
//            if (!authoritySet.contains(StaffAuthorityEnum.STAFF.name())) {
//                authoritySet.add(StaffAuthorityEnum.STAFF.name());
//            }
//            if (staff.getAuthorities().contains("SA")) {
//                staffAuthorityService.fillAuth(authoritySet, StaffAuthorityEnum.SA);
//            }
//            List<GrantedAuthority> list =
//                    AuthorityUtils.createAuthorityList(authoritySet.toArray(new String[]{}));
//            authorityList.addAll(list);
//        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (Role role : user.getRoles()){
        grantedAuthorities.add(new SimpleGrantedAuthority("user"));
//        }

//        request.getSession().setAttribute("auth", user.getId());
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassWord(), grantedAuthorities);


//        User.UserBuilder userBuilder = User.withUsername(userId);
//        userBuilder.password("N/A");
//
//        return userBuilder.build();
    }


    public User findByUsername(String username) {
        return userRepo.findByUserName(username);
    }

    public void savePassWord(User user) {
        user.setPassWord(passwordEncoder.encode(user.getPassWord()));
//        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepo.save(user);
    }
}
