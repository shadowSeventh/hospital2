package top.ball.rice.hospital.server.resource.hospital.common.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ball.rice.hospital.domain.QUser;
import top.ball.rice.hospital.domain.User;
import top.ball.rice.hospital.repo.UserRepo;
import top.ball.rice.hospital.server.common.ErrStatus;
import top.ball.rice.hospital.server.common.UniResp;
import top.ball.rice.hospital.service.util.errorHandler.ErrStatusException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Component()
@Path("/login")
public class LoginResource {

    @Autowired
    private UserRepo userRepo;

    @Path("/{id}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<String> login(
            @QueryParam(value = "userName") String userName,
            @QueryParam(value = "passWord") String passWord
    ) {

        User user=userRepo.findOne(
                QUser.user.userName.eq(userName)
        );

        if (user==null){
            throw new ErrStatusException(ErrStatus.UNLOGIN,"用户未登录");
        }

        UniResp<String> resp = new UniResp<>();
        resp.setStatus(200);
        resp.setData("登录成功");
        return resp;
    }
}
