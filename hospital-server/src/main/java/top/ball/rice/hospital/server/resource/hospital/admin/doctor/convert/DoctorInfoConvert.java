package top.ball.rice.hospital.server.resource.hospital.admin.doctor.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import top.ball.rice.hospital.domain.Doctor;
import top.ball.rice.hospital.domain.User;
import top.ball.rice.hospital.repo.UserRepo;
import top.ball.rice.hospital.server.resource.hospital.admin.doctor.dto.DoctorInfoResp;


@Component
public class DoctorInfoConvert implements Converter<Doctor, DoctorInfoResp> {


    @Autowired
    private UserRepo userRepo;

    @Override
    public DoctorInfoResp convert(Doctor doctor) {

        User user = userRepo.findOne(doctor.getUserId());
        Assert.notNull(user, "医生信息错误！");

        DoctorInfoResp resp = new DoctorInfoResp();

        resp.setNickName(user.getNickName());
        resp.setDepartment(doctor.getDepartment());
        resp.setPhone(user.getPhone());
        resp.setSex(user.getSex());
        resp.setAddr(user.getAddr());
        resp.setStatusCode(doctor.getStatus().getCode());
        resp.setStatusDesp(doctor.getStatus().getDesp());
        resp.setDesp(doctor.getDesp());

        return resp;
    }
}
