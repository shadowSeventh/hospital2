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

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<SuffererInfoResp> info(
            @PathParam(value = "id") String id
    ) {

        Sufferer sufferer = suffererRepo.findOne(id);
        Assert.notNull(sufferer, "患者信息错误");
        SuffererInfoResp infoResp = conversionService.convert(sufferer, SuffererInfoResp.class);
        UniResp<SuffererInfoResp> resp = new UniResp<>();
        resp.setStatus(200);
        resp.setData(infoResp);
        return resp;
    }

    @Path("")
    @GET
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

        userRepo.save(user);

        sufferer.setUserId(user.getId());
        sufferer.setHeight(req.getHeight());
        sufferer.setWeight(req.getWeight());

        suffererRepo.save(sufferer);

        UniResp<String> resp = new UniResp<>();
        resp.setData("保存成功");
        resp.setStatus(200);
        return resp;
    }
}
