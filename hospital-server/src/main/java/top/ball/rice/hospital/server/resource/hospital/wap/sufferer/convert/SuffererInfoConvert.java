package top.ball.rice.hospital.server.resource.hospital.wap.sufferer.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import top.ball.rice.hospital.domain.Sufferer;
import top.ball.rice.hospital.domain.User;
import top.ball.rice.hospital.repo.UserRepo;
import top.ball.rice.hospital.server.resource.hospital.wap.sufferer.dto.SuffererInfoResp;

@Component
public class SuffererInfoConvert implements Converter<Sufferer, SuffererInfoResp> {

    @Autowired
    private UserRepo userRepo;

    @Override
    public SuffererInfoResp convert(Sufferer source) {

        SuffererInfoResp resp = new SuffererInfoResp();

        User user = userRepo.findOne(source.getUserId());

        resp.setId(source.getId());
        resp.setUserName(user.getUserName());
        resp.setNickName(user.getNickName());
        resp.setPassWord(user.getPassWord());
        resp.setHeadImg(user.getHeadImg());
        resp.setPhone(user.getPhone());
        resp.setSex(user.getSex());
        resp.setAge(user.getAge());
        resp.setIDNum(user.getIDNum());
        resp.setContacts(user.getContacts());
        resp.setTelephone(user.getTelephone());
        resp.setHeight(source.getHeight());
        resp.setWeight(source.getWeight());
        return resp;
    }
}
