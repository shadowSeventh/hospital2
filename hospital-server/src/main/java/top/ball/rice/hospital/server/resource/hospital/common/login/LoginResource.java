package top.ball.rice.hospital.server.resource.hospital.common.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Component;
import top.ball.rice.hospital.domain.QUser;
import top.ball.rice.hospital.domain.User;
import top.ball.rice.hospital.repo.UserRepo;
import top.ball.rice.hospital.server.common.ErrStatus;
import top.ball.rice.hospital.server.common.UniResp;
import top.ball.rice.hospital.service.service.SecurityService;
import top.ball.rice.hospital.service.util.errorHandler.ErrStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Component()
@Path("/login")
public class LoginResource {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SecurityService securityService;

    @Autowired
    @Qualifier("authenticationManager") // bean id 在 <authentication-manager> 中设置
    private AuthenticationManager authManager;

    @Path("")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<String> login(
            @QueryParam(value = "phone") String phone,
            @QueryParam(value = "passWord") String passWord
    ) {

        User user = userRepo.findOne(
                QUser.user.phone.eq(phone)
        );
        if (user == null) {
            throw new ErrStatusException(ErrStatus.UNREGISTER, "用户未注册");
        }
        securityService.autoLogin(user.getUserName(), passWord);

        UniResp<String> resp = new UniResp<>();
        resp.setStatus(200);
        resp.setData("登录成功");
        return resp;
    }
}
