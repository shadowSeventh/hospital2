package net.kingsilk.qh.hospital.server.conf;

import net.kingsilk.qh.hospital.service.HospitalProperties;
import net.kingsilk.qh.oauth.api.user.UserApi;
import net.kingsilk.qh.oauth.api.user.addr.AddrApi;
import net.kingsilk.qh.oauth.api.user.org.OrgApi;
import net.kingsilk.qh.oauth.api.user.org.orgStaff.OrgStaffApi;
import net.kingsilk.qh.oauth.client.impl.user.UserApiImpl;
import net.kingsilk.qh.oauth.client.impl.user.addr.AddrApiImpl;
import net.kingsilk.qh.oauth.client.impl.user.org.OrgApiImpl;
import net.kingsilk.qh.oauth.client.impl.user.org.orgStaff.OrgStaffApiImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;


@Configuration
public class QhOauthApiConf {

    @Bean
    public UserApi qhOauthUserApi(

            @Qualifier("oauthRestTemplate")
                    RestOperations restOperations,
            HospitalProperties hospitalProperties
    ) {
        return new UserApiImpl(restOperations, hospitalProperties.getOauthUt().getBasePath());
    }

    @Bean
    public OrgApi qhOauthOrgApi(
            @Qualifier("oauthRestTemplate")
                    RestOperations restOperations,
            HospitalProperties hospitalProperties
    ) {
        return new OrgApiImpl(restOperations, hospitalProperties.getOauthUt().getBasePath());
    }

    @Bean
    public OrgStaffApi qhOauthOrgStaffApi(
            @Qualifier("oauthRestTemplate")
                    RestOperations restOperations,
            HospitalProperties hospitalProperties
    ) {
        return new OrgStaffApiImpl(restOperations, hospitalProperties.getOauthUt().getBasePath());
    }

    @Bean
    public AddrApi qhOauthAddrApi(
            @Qualifier("oauthRestTemplate")
                    RestOperations restOperations,
            HospitalProperties hospitalProperties
    ) {
        return new AddrApiImpl(restOperations, hospitalProperties.getOauthUt().getBasePath());
    }

}
