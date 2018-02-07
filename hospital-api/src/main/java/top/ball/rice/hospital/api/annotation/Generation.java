package top.ball.rice.hospital.api.annotation;


import top.ball.rice.hospital.api.core.OauthType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Generation {

    /**
     * oauth认证方式
     * @return
     */
    OauthType oauthType() default OauthType.USER ;


}
