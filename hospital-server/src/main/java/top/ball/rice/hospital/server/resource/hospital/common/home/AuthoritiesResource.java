package top.ball.rice.hospital.server.resource.hospital.common.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ball.rice.hospital.domain.QStaff;
import top.ball.rice.hospital.domain.Staff;
import top.ball.rice.hospital.domain.User;
import top.ball.rice.hospital.repo.StaffRepo;
import top.ball.rice.hospital.repo.UserRepo;
import top.ball.rice.hospital.server.common.ErrStatus;
import top.ball.rice.hospital.server.common.UniResp;
import top.ball.rice.hospital.service.service.SecService;
import top.ball.rice.hospital.service.util.errorHandler.ErrStatusException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.HashSet;
import java.util.Set;

@Component()
@Path("/authorities")
public class AuthoritiesResource {

    @Autowired
    private SecService secService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StaffRepo staffRepo;

    @Path("")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<Set<String>> login(
            @QueryParam(value = "phone") String phone,
            @QueryParam(value = "passWord") String passWord
    ) {

        String userId = secService.curUserId();
        if (userId == null) {
            throw new ErrStatusException(ErrStatus.UNLOGIN, "用户未登录");
        }
        User user = userRepo.findOne(userId);

        Set<String> authorities = new HashSet<>();
        authorities.add("SUFFER");

        Staff staff = staffRepo.findOne(
                QStaff.staff.userId.eq(userId)
        );
        if (staff!=null){
            authorities.add("ADMIN");
        }

        UniResp<Set<String>> uniResp = new UniResp<>();
//        User user = userRepo.findOne(
//                QUser.user.phone.eq(phone)
//        );
//        if (user == null) {
//            throw new ErrStatusException(ErrStatus.UNREGISTER, "用户未注册");
//        }
//        securityService.autoLogin(user.getUserName(), passWord);
//
//        request.getSession()
//                .setAttribute("SPRING_SECURITY_CONTEXT","222222222");
        uniResp.setData(authorities);
        uniResp.setStatus(200);
        return uniResp;
    }
}
