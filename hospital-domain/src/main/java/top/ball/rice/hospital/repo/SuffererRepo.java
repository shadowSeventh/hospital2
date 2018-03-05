package top.ball.rice.hospital.repo;


import org.springframework.stereotype.Repository;
import top.ball.rice.hospital.domain.Sufferer;
import top.ball.rice.hospital.domain.User;

@Repository
public interface SuffererRepo extends BaseRepo<Sufferer, String> {
    Sufferer findByUserId(String userId);
}
