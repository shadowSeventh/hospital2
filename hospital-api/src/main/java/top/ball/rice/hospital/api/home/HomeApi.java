package top.ball.rice.hospital.api.home;


import top.ball.rice.hospital.api.UniResp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/brandApp/{brandAppId}/shop/{shopId}/bargain/home")
public interface HomeApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
//    @PreAuthorize("isAuthenticated() && hasAnyAuthority('STAFF_R')")
    UniResp<String> getBargainAppId(
            @PathParam(value = "brandAppId") String brandAppId,
            @PathParam(value = "shopId") String shopId);

}
