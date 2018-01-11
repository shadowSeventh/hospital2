package top.ball.rice.hospital.repo;


import org.springframework.stereotype.Repository;
import top.ball.rice.hospital.domain.Article;

@Repository
public interface ArticleRepo extends BaseRepo<Article, String> {

}
