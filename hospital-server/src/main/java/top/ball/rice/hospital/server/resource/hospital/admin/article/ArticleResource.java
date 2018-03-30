package top.ball.rice.hospital.server.resource.hospital.admin.article;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import top.ball.rice.hospital.core.ArticleStatusEnum;
import top.ball.rice.hospital.domain.Article;
import top.ball.rice.hospital.domain.QArticle;
import top.ball.rice.hospital.repo.ArticleRepo;
import top.ball.rice.hospital.server.common.UniPageReq;
import top.ball.rice.hospital.server.common.UniPageResp;
import top.ball.rice.hospital.server.common.UniResp;
import top.ball.rice.hospital.server.resource.hospital.admin.article.dto.ArticleInfoResp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

import static com.querydsl.core.types.dsl.Expressions.allOf;

@Component
@Path("/admin/article")
public class ArticleResource {

    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    @Qualifier("mvcConversionService")
    private ConversionService conversionService;

    @Path("")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<String> save(
            @QueryParam(value = "id")
                    String id,
            @QueryParam(value = "title")
                    String title,
            @QueryParam(value = "headImg")
                    String headImg,
            @QueryParam(value = "type")
                    String type,
            @QueryParam(value = "content")
                    String content,
            @QueryParam(value = "status")
                    String status
    ) {
        Article article;
        if (id == null) {
            article = new Article();
            article.setDateCreated(new Date());
            article.setLastModifiedDate(new Date());
        } else {
            article = articleRepo.findOne(id);
        }

        article.setTitle(title);
        if ("true".equals(type)) {
            article.setType("head");
        } else {
            article.setType("content");
        }


        article.setHeadImg(headImg);
        article.setContent(content);
        article.setStatus(ArticleStatusEnum.valueOf(status));
        articleRepo.save(article);

        UniResp<String> resp = new UniResp<>();
        resp.setData("保存成功");
        resp.setStatus(200);
        return resp;
    }


    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<UniPageResp<ArticleInfoResp>> page(
            @BeanParam UniPageReq req,
            @QueryParam(value = "title") String title
    ) {
        PageRequest pageRequest = new PageRequest(req.getPage(), req.getSize(),
                new Sort(new Sort.Order(Sort.Direction.DESC, "dateCreated")));
        Page<Article> page = articleRepo.findAll(
                allOf(
                        title != null ? QArticle.article.title.like("%" + title + "%") : null,
                        QArticle.article.deleted.ne(true)
                ), pageRequest
        );
        UniPageResp<ArticleInfoResp> uniPageResp = conversionService.convert(page, UniPageResp.class);

        Page<ArticleInfoResp> resp = page.map(article ->
                conversionService.convert(article, ArticleInfoResp.class)
        );
        uniPageResp.setContent(resp.getContent());
        UniResp<UniPageResp<ArticleInfoResp>> uniResp = new UniResp<>();
        uniResp.setData(uniPageResp);
        uniResp.setStatus(200);
        return uniResp;
    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<String> changeStatus(
            @PathParam(value = "id")
                    String id,
            @QueryParam(value = "status")
                    String status
    ) {
        Article article = articleRepo.findOne(id);
        Assert.notNull(article, "文章信息错误！");
        article.setStatus(ArticleStatusEnum.valueOf(status));
        articleRepo.save(article);

        UniResp<String> resp = new UniResp<>();
        resp.setData("保存成功");
        resp.setStatus(200);
        return resp;
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public UniResp<String> delete(
            @PathParam(value = "id")
                    String id
    ) {
        Article article = articleRepo.findOne(id);
        Assert.notNull(article, "文章信息错误！");
        article.setDeleted(true);
        articleRepo.save(article);
        UniResp<String> resp = new UniResp<>();
        resp.setData("保存成功");
        resp.setStatus(200);
        return resp;
    }

}
