package top.ball.rice.hospital.domain;

import top.ball.rice.hospital.core.OrderStatusEnum;

import javax.persistence.Entity;
import java.util.Date;

/**
 * 病房预约记录表
 */
@Entity
public class SickRoomOrderRecord extends Base{

    /**
     * 患者ID
     */
    private String suffererId;

    /**
     * 病房ID
     */
    private String sickroomId;

    /**
     * 预约状态
     */
    private OrderStatusEnum status;

    /**
     * 最后期限
     */
    private Date deadline;

    public String getSuffererId() {
        return suffererId;
    }

    public void setSuffererId(String suffererId) {
        this.suffererId = suffererId;
    }

    public String getSickroomId() {
        return sickroomId;
    }

    public void setSickroomId(String sickroomId) {
        this.sickroomId = sickroomId;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
