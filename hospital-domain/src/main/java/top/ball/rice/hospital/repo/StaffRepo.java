package top.ball.rice.hospital.repo;


import org.springframework.stereotype.Repository;
import top.ball.rice.hospital.domain.Staff;

@Repository("staffRepo")
public interface StaffRepo extends BaseRepo<Staff, String> {

}
