package top.ball.rice.hospital.server.resource.hospital.wap.sufferer;


import org.springframework.stereotype.Component;
import top.ball.rice.hospital.server.common.UniResp;
import top.ball.rice.hospital.server.resource.hospital.admin.article.dto.ArticleInfoResp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component("articleResourceWap")
@Path("/wap/sufferer")
public class SuffererResource {


    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<ArticleInfoResp> info(
            @PathParam(value = "id") String id
    ) {

        return null;
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<String> save(
            @PathParam(value = "id") String id
    ) {

        return null;
    }
}
