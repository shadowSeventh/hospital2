package top.ball.rice.hospital.domain;

import top.ball.rice.hospital.core.OrderStatusEnum;

import javax.persistence.Entity;

@Entity
public class DoctorOrderRecord extends Base {
    /**
     * 患者ID
     */
    private String suffererId;

    /**
     * 病房ID
     */
    private String doctorId;

    /**
     * 预约状态
     */
    private OrderStatusEnum status;

    public String getSuffererId() {
        return suffererId;
    }

    public void setSuffererId(String suffererId) {
        this.suffererId = suffererId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }
}
