package top.ball.rice.hospital.service.util;

import com.github.shadowseventh.distributed.lock.api.lockKey.CustomLockKey;
import org.springframework.stereotype.Component;

@Component
public class DisLockKey implements CustomLockKey {


    @Override
    public String lockKey() {
        return "2222222222222222222";
    }
}
