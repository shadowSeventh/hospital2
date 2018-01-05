package top.ball.rice.hospital.api.bargainApp.authorities;

import top.ball.rice.hospital.api.UniResp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;


@Path("/bargainApp/{bargainAppId}/authorities")
public interface AuthoritiesApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    UniResp<Set<String>> getAuthorities(
            @PathParam(value = "bargainAppId") String bargainAppId
    );


    @GET
    @Path("/userId/{userId}/orgId/{orgId}")
    @Produces(MediaType.APPLICATION_JSON)
    UniResp<String> setSAStaff(
            @PathParam(value = "bargainAppId") String bargainAppId,
            @PathParam(value = "userId") String userId,
            @PathParam(value = "orgId") String orgId
    );
}
