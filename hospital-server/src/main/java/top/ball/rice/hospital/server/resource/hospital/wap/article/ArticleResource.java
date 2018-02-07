package top.ball.rice.hospital.server.resource.hospital.wap.article;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import top.ball.rice.hospital.domain.Article;
import top.ball.rice.hospital.domain.QArticle;
import top.ball.rice.hospital.repo.ArticleRepo;
import top.ball.rice.hospital.server.common.UniPageReq;
import top.ball.rice.hospital.server.common.UniPageResp;
import top.ball.rice.hospital.server.common.UniResp;
import top.ball.rice.hospital.server.resource.hospital.admin.article.dto.ArticleInfoResp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static com.querydsl.core.types.dsl.Expressions.allOf;

@Component("articleResourceWap")
@Path("/wap/article")
public class ArticleResource {

    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    @Qualifier("mvcConversionService")
    private ConversionService conversionService;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<ArticleInfoResp> info(
            @PathParam(value = "id") String id
    ) {

        Article article = articleRepo.findOne(id);

        UniResp<ArticleInfoResp> resp = new UniResp<>();
        resp.setData(conversionService.convert(article, ArticleInfoResp.class));
        resp.setStatus(200);
        return resp;
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<UniPageResp<ArticleInfoResp>> list(
            @BeanParam UniPageReq req,
            @QueryParam(value = "title") String title
    ) {
        PageRequest pageRequest = new PageRequest(req.getPage(), req.getSize(),
                new Sort(new Sort.Order(Sort.Direction.ASC, "dateCreated")));

        UniPageResp<ArticleInfoResp> uniPageResp = new UniPageResp<>();

        articleRepo.findAll(
                allOf(
                        title != null ? QArticle.article.title.eq(title) : null,
                        QArticle.article.deleted.ne(true)
                ), pageRequest
        ).forEach(
                article -> {
                    uniPageResp.getContent().add(conversionService.convert(article, ArticleInfoResp.class));
                }
        );

        UniResp<UniPageResp<ArticleInfoResp>> resp = new UniResp<>();
        resp.setData(uniPageResp);
        resp.setStatus(200);
        return resp;
    }
}
