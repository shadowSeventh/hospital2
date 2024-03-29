package top.ball.rice.hospital.server.resource.hospital.wap.sufferer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import top.ball.rice.hospital.domain.Sufferer;
import top.ball.rice.hospital.domain.User;
import top.ball.rice.hospital.repo.SuffererRepo;
import top.ball.rice.hospital.repo.UserRepo;
import top.ball.rice.hospital.server.common.UniResp;
import top.ball.rice.hospital.server.resource.hospital.wap.sufferer.dto.SuffererInfoResp;
import top.ball.rice.hospital.server.resource.hospital.wap.sufferer.dto.SuffererSaveReq;
import top.ball.rice.hospital.service.service.SecService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Component("suffererResourceWap")
@Path("/wap/sufferer")
public class SuffererResource {

    @Autowired
    private SuffererRepo suffererRepo;

    @Autowired
    @Qualifier("mvcConversionService")
    private ConversionService conversionService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SecService secService;

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<SuffererInfoResp> info(
    ) {
        String userName=secService.curUserId();
        User user = userRepo.findByUserName(userName);
        Sufferer sufferer = suffererRepo.findByUserId(user.getId());
        Assert.notNull(sufferer, "患者信息错误");
        SuffererInfoResp infoResp = conversionService.convert(sufferer, SuffererInfoResp.class);
        UniResp<SuffererInfoResp> resp = new UniResp<>();
        resp.setStatus(200);
        resp.setData(infoResp);
        return resp;
    }

    @Path("")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<String> save(
            @BeanParam SuffererSaveReq req
    ) {

        Sufferer sufferer;
        if (req.getId() == null) {
            sufferer = new Sufferer();
            sufferer.setDateCreated(new Date());
            sufferer.setLastModifiedDate(new Date());
        } else {
            sufferer = suffererRepo.findOne(req.getId());
            Assert.notNull(sufferer, "账号信息错误");
        }

        User user;

        if (sufferer.getUserId() == null) {
            user = new User();
            user.setDateCreated(new Date());
            user.setLastModifiedDate(new Date());
        } else {
            user = userRepo.findOne(sufferer.getUserId());

        }


        user.setUserName(req.getUserName());
        user.setNickName(req.getNickName());
        user.setPassWord(req.getPassWord());
        user.setHeadImg(req.getHeadImg());
        user.setPhone(req.getPhone());
        user.setSex(req.getSex());
        user.setAge(req.getAge());
        user.setIDNum(req.getIDNum());
        user.setContacts(req.getContacts());
        user.setTelephone(req.getTelephone());
        user.setHeight(user.getHeight());
        user.setWeight(user.getWeight());

        userRepo.save(user);

        sufferer.setUserId(user.getId());

        suffererRepo.save(sufferer);

        UniResp<String> resp = new UniResp<>();
        resp.setData("保存成功");
        resp.setStatus(200);
        return resp;
    }
}
