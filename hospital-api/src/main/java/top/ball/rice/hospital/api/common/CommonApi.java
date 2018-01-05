package top.ball.rice.hospital.api.common;

import top.ball.rice.hospital.api.UniResp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * Created by lit on 17/11/8.
 */

@Path("/common")
public interface CommonApi {

    @Path("/getAdcList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    UniResp<List<Map<String, Object>>> getAdcList();

}
