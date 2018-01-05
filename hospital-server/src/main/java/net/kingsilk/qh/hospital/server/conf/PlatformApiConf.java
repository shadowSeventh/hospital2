package net.kingsilk.qh.hospital.server.conf;

import net.kingsilk.qh.hospital.service.HospitalProperties;
import net.kingsilk.qh.platform.api.brandApp.BrandAppApi;
import net.kingsilk.qh.platform.client.BrandAppApiImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

@Configuration
public class PlatformApiConf {

    @Bean
    public BrandAppApi brandAppApi(
            @Qualifier("restTemplate")
                    RestOperations restOperations,
            HospitalProperties hospitalProperties

    ) {
        return new BrandAppApiImpl(restOperations, hospitalProperties.getPlatformUt().getBasePath());
    }

}
