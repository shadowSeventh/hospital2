package top.ball.rice.hospital.repo;


import org.springframework.stereotype.Repository;
import top.ball.rice.hospital.domain.Nurse;

@Repository
public interface NurseRepo extends BaseRepo<Nurse, String> {
}
