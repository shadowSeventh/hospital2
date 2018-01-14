package top.ball.rice.hospital.server.resource.hospital.admin.article.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import top.ball.rice.hospital.domain.Article;
import top.ball.rice.hospital.server.resource.hospital.admin.article.dto.ArticleInfoResp;

@Component
public class ArticleInfoConvert implements Converter<Article, ArticleInfoResp> {
    @Override
    public ArticleInfoResp convert(Article source) {
        ArticleInfoResp resp = new ArticleInfoResp();
        resp.setId(source.getId());
        resp.setTitle(source.getTitle());
        resp.setHeadImg(source.getHeadImg());
        resp.setContent(source.getContent());
        resp.setStatusCode(source.getStatus().getCode());
        resp.setStatusDesp(source.getStatus().getDesp());
        return resp;
    }
}
