package top.ball.rice.hospital.server.resource.hospital.wap.index;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ball.rice.hospital.core.ArticleStatusEnum;
import top.ball.rice.hospital.domain.Article;
import top.ball.rice.hospital.repo.ArticleRepo;
import top.ball.rice.hospital.server.common.UniResp;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Component
@Path("/hospital/article")
public class ArticleResource {

    @Autowired
    private ArticleRepo articleRepo;

    @Path("")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<String> save(
            @QueryParam(value = "id")
                    String id,
            @QueryParam(value = "title")
                    String title,
            @QueryParam(value = "headImg")
                    String headImg,
            @QueryParam(value = "content")
                    String content,
            @QueryParam(value = "status")
                    String status
    ) {
        Article article = articleRepo.findOne(id);
        if (article == null) {
            article = new Article();
            article.setDateCreated(new Date());
            article.setLastModifiedDate(new Date());
        }

        article.setTitle(title);
        article.setHeadImg(headImg);
        article.setContent(content);
        article.setStatus(ArticleStatusEnum.valueOf(status));
        articleRepo.save(article);

        UniResp<String> resp = new UniResp<>();
        resp.setData("保存成功");
        resp.setStatus(200);
        return resp;
    }
}
