package top.ball.rice.hospital.domain;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Doctor extends Base {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String desp;

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }
}
