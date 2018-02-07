package top.ball.rice.hospital.repo;


import org.springframework.stereotype.Repository;
import top.ball.rice.hospital.domain.Doctor;

@Repository
public interface DoctorRepo extends BaseRepo<Doctor, String> {
}
