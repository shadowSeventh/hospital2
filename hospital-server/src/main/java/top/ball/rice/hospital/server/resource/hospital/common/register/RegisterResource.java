package top.ball.rice.hospital.server.resource.hospital.common.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import top.ball.rice.hospital.domain.QUser;
import top.ball.rice.hospital.domain.Sufferer;
import top.ball.rice.hospital.domain.User;
import top.ball.rice.hospital.repo.SuffererRepo;
import top.ball.rice.hospital.repo.UserRepo;
import top.ball.rice.hospital.server.common.UniResp;
import top.ball.rice.hospital.service.service.SecService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import static com.querydsl.core.types.dsl.Expressions.allOf;

@Component
@Path("/register")
public class RegisterResource {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SecService secService;

    @Autowired
    private SuffererRepo suffererRepo;

    @Path("")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<String> register(
            @QueryParam(value = "phone") String phone,
            @QueryParam(value = "passWord") String passWord
    ) {


        User user = userRepo.findOne(
                allOf(
                        QUser.user.phone.eq(phone),
                        QUser.user.deleted.ne(true)
                )
        );
        Assert.isTrue(user == null, "手机号已使用！");

        user = new User();
        user.setUserName("u" + phone);
        user.setPhone(phone);
        user.setPassWord(secService.encodePassword(passWord));

        userRepo.save(user);

        Sufferer sufferer = new Sufferer();
        sufferer.setUserId(user.getId());
        suffererRepo.save(sufferer);

        UniResp resp = new UniResp();
        resp.setData("创建成功");
        resp.setStatus(200);
        return resp;
    }
}
