package top.ball.rice.hospital.repo;


import org.springframework.stereotype.Repository;
import top.ball.rice.hospital.domain.User;

@Repository
public interface UserRepo extends BaseRepo<User, String> {
    User findByUserName(String userName);
    User findByPhone(String phone);
}
