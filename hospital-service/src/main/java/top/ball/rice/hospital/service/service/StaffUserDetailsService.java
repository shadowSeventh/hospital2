package top.ball.rice.hospital.service.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StaffUserDetailsService implements UserDetailsService {

    public StaffUserDetailsService() {

    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {


        User.UserBuilder userBuilder = User.withUsername(userId);
        userBuilder.password("N/A");

        return userBuilder.build();
    }
}
