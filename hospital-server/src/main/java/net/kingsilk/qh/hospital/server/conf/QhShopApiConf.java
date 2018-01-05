package net.kingsilk.qh.hospital.server.conf;

import net.kingsilk.qh.hospital.service.HospitalProperties;
import net.kingsilk.qh.shop.api.brandApp.shop.ShopApi;
import net.kingsilk.qh.shop.client.impl.ShopApiImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

@Configuration
public class QhShopApiConf {

    @Bean
    public ShopApi qhShopApi(

            @Qualifier("oauthRestTemplate")
                    RestOperations restOperations,
            HospitalProperties hospitalProperties
    ) {
        return new ShopApiImpl(restOperations, hospitalProperties.getShopUt().getBasePath());
    }
}
