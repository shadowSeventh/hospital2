package top.ball.rice.hospital.server.conf;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import top.ball.rice.hospital.server.resource.bargainApp.authorities.AuthoritiesResource;
import top.ball.rice.hospital.server.resource.home.HomeResource;

import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;


@Configuration
public class JerseyConf {

    @Autowired
    ApplicationContext ctx;


    @Bean
    ResourceConfig resourceConfig(Environment environment) {


        ResourceConfig resourceConfig = new ResourceConfig();

        // 强制 响应的contentType中追加 charset
        // http://stackoverflow.com/questions/5514087/jersey-rest-default-character-encoding/20569571
        resourceConfig.register((ContainerResponseFilter) (request, response) -> {
            MediaType type = response.getMediaType();
            if (type != null) {
                String contentType = type.toString();
                if (!contentType.contains("charset")) {
                    contentType = contentType + ";charset=utf-8";
                    response.getHeaders().putSingle("Content-Type", contentType);
                }
            }
        });


        // 根据文档，需要手动一一将RESTful controller 添加至此。
        // http://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/reference/htmlsingle/#boot-features-jersey

        // 统一要求：全部注册实现类，而非接口类

        resourceConfig.register(AuthoritiesResource.class);
        resourceConfig.register(HomeResource.class);
        resourceConfig.property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
        resourceConfig.property(ServerProperties.PROCESSING_RESPONSE_ERRORS_ENABLED, true);
        return resourceConfig;
    }
}
