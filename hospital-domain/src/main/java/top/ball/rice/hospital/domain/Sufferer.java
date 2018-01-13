package top.ball.rice.hospital.domain;


import javax.persistence.Entity;

@Entity
public class Sufferer extends Base {

    private String userId;

    /**
     * 身高
     */
    private Integer height;

    /**
     * 体重
     */
    private Integer weight;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
