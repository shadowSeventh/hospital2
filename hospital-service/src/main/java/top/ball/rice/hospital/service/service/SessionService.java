package top.ball.rice.hospital.service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import top.ball.rice.hospital.service.authInfo.UserAuthInfo;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
@Service
public class SessionService {

    // FIXME: 分布式情况下该锁仍会有问题

    // ---------------------- PhoneAuthInfo
    @Nonnull
    public List<UserAuthInfo> getUserAuthInfos() {


        synchronized (RequestContextHolder.currentRequestAttributes().getSessionMutex()) {
            List<UserAuthInfo> infos = (List<UserAuthInfo>) RequestContextHolder.currentRequestAttributes().getAttribute(
                    UserAuthInfo.SESSION_KEY,
                    RequestAttributes.SCOPE_SESSION
            );

            if (infos == null) {
                infos = new LinkedList<>();
                setUserAuthInfos(infos);
            }
            return infos;
        }
    }


    public void setUserAuthInfos(List<UserAuthInfo> infos) {

        synchronized (RequestContextHolder.currentRequestAttributes().getSessionMutex()) {
            RequestContextHolder.currentRequestAttributes().setAttribute(
                    UserAuthInfo.SESSION_KEY,
                    infos,
                    RequestAttributes.SCOPE_SESSION
            );
        }
    }

    public void add(UserAuthInfo info) {
        synchronized (RequestContextHolder.currentRequestAttributes().getSessionMutex()) {
            List<UserAuthInfo> infos = getUserAuthInfos();
            infos.add(info);
            setUserAuthInfos(infos);
        }
    }


}
