package top.ball.rice.hospital.domain;

import top.ball.rice.hospital.core.StaffStatusEnum;

import javax.persistence.Entity;

@Entity
public class Nurse extends Base {

    private String userId;
    /**
     * 科室
     */
    private String department;

    /**
     * 简介
     */
    private String desp;

    /**
     * 状态
     */
    private StaffStatusEnum status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public StaffStatusEnum getStatus() {
        return status;
    }

    public void setStatus(StaffStatusEnum status) {
        this.status = status;
    }
}
