package top.ball.rice.hospital.domain;


import javax.persistence.Entity;

@Entity
public class Sufferer extends Base {

    private String userId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
