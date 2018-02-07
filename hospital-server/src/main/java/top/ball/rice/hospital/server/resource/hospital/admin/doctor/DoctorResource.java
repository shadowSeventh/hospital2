package top.ball.rice.hospital.server.resource.hospital.admin.doctor;


import com.github.shadowseventh.distributed.lock.api.DisLock;
import com.github.shadowseventh.distributed.lock.core.LockTypeEnum;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import top.ball.rice.hospital.core.StaffStatusEnum;
import top.ball.rice.hospital.domain.Doctor;
import top.ball.rice.hospital.domain.QDoctor;
import top.ball.rice.hospital.domain.QUser;
import top.ball.rice.hospital.domain.User;
import top.ball.rice.hospital.repo.DoctorRepo;
import top.ball.rice.hospital.repo.UserRepo;
import top.ball.rice.hospital.server.common.UniPageResp;
import top.ball.rice.hospital.server.common.UniResp;
import top.ball.rice.hospital.server.resource.hospital.admin.doctor.dto.DoctorInfoResp;
import top.ball.rice.hospital.server.resource.hospital.admin.doctor.dto.DoctorPageReq;
import top.ball.rice.hospital.server.resource.hospital.admin.doctor.dto.DoctorSaveReq;
import top.ball.rice.hospital.service.util.DbUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static com.querydsl.core.types.dsl.Expressions.allOf;

@Component
@Path("/admin/doctor")
public class DoctorResource {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    @Qualifier("mvcConversionService")
    private ConversionService conversionService;


    @Path("")
    @POST
    @Produces(MediaType.APPLICATION_JSON)

    public UniResp<String> save(
            DoctorSaveReq req
    ) {

        User user = new User();
        user.setNickName(req.getNickName());
        user.setPhone(req.getPhone());
        user.setSex(req.getSex());
        user.setAddr(req.getAddr());
        userRepo.save(user);

        Doctor doctor = new Doctor();
        doctor.setUserId(user.getId());
        doctor.setDepartment(req.getDepartment());
        doctor.setDesp(req.getDesp());
        doctor.setStatus(StaffStatusEnum.valueOf(req.getStatus()));
        doctorRepo.save(doctor);

        UniResp<String> resp = new UniResp<>();
        resp.setStatus(200);
        resp.setData("保存成功！");
        return resp;
    }


    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<String> update(
            @PathParam(value = "id") String id,
            DoctorSaveReq req
    ) {

        Doctor doctor = doctorRepo.findOne(id);
        Assert.notNull(doctor, "医生信息错误！");

        User user = userRepo.findOne(doctor.getUserId());
        Assert.notNull(user, "医生信息错误！");

        user.setNickName(req.getNickName());
        user.setPhone(req.getPhone());
        user.setSex(req.getSex());
        user.setAddr(req.getAddr());
        userRepo.save(user);


        doctor.setUserId(user.getId());
        doctor.setDepartment(req.getDepartment());
        doctor.setDesp(req.getDesp());
        doctor.setStatus(StaffStatusEnum.valueOf(req.getStatus()));
        doctorRepo.save(doctor);

        UniResp<String> resp = new UniResp<>();
        resp.setStatus(200);
        resp.setData("保存成功！");
        return resp;
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<DoctorInfoResp> info(
            @PathParam(value = "id") String id
    ) {

        Doctor doctor = doctorRepo.findOne(id);
        Assert.notNull(doctor, "医生信息错误！");

        DoctorInfoResp infoResp = conversionService.convert(doctor, DoctorInfoResp.class);

        UniResp<DoctorInfoResp> resp = new UniResp<>();
        resp.setStatus(200);
        resp.setData(infoResp);

        return resp;
    }

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<UniPageResp<DoctorInfoResp>> page(
            @BeanParam DoctorPageReq req
    ) {

        PageRequest pageRequest = new PageRequest(req.getPage(), req.getSize(),
                new Sort(new Sort.Order(Sort.Direction.DESC, "dateCreated")));

        List<String> userIds = new ArrayList<>();
        if (req.getKeyWord() != null) {
            userRepo.findAll(
                    allOf(
                            QUser.user.deleted.ne(true),
                            QUser.user.nickName.like(req.getKeyWord() + "%")
                    )
            ).forEach(
                    user -> userIds.add(user.getId())
            );
        }


        Page<Doctor> doctors = doctorRepo.findAll(
                allOf(
                        QDoctor.doctor.deleted.ne(true),
                        req.getDepartment() != null ? QDoctor.doctor.department.eq(req.getDepartment()) : null,
                        req.getStatus() != null ? QDoctor.doctor.status.eq(StaffStatusEnum.valueOf(req.getStatus())) : null,
//                        userIds.size() > 0 ? QDoctor.doctor.userId.in(userIds) : null
                        userIds.size() > 0 ? DbUtil.opIn(QDoctor.doctor.userId, userIds) : null

                ), pageRequest
        );

        UniPageResp<DoctorInfoResp> uniPageResp = conversionService.convert(doctors, UniPageResp.class);

        Page<DoctorInfoResp> resp = doctors.map(
                doctor -> conversionService.convert(doctor, DoctorInfoResp.class)
        );
        uniPageResp.setContent(resp.getContent());
        UniResp<UniPageResp<DoctorInfoResp>> uniResp = new UniResp<>();
        uniResp.setData(uniPageResp);
        uniResp.setStatus(200);
        return uniResp;
    }

}
