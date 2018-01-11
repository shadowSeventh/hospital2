package top.ball.rice.hospital.domain;

import top.ball.rice.hospital.core.ArticleStatusEnum;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Article extends Base {

    private String title;

    private String headImg;

    private String content;

    private ArticleStatusEnum status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArticleStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ArticleStatusEnum status) {
        this.status = status;
    }
}