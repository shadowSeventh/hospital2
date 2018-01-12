package top.ball.rice.hospital.domain;

import javax.persistence.Entity;
import java.util.Date;

/**
 * 住院记录表
 */
@Entity
public class HospitalizationRecord {

    /**
     * 病房ID
     */
    private  String sickRoomId;

    /**
     * 预约记录ID
     */
    private String sickRoomOrderId;

    /**
     * 入住时间
     */
    private Date startTime;

    /**
     * 出院时间
     */
    private Date endTime;

    /**
     * 换房记录，ps. 其实就是住院记录
     */
    private String changeWard;

    /**
     * 患者ID
     */
    private String suffererId;

    public String getSickRoomId() {
        return sickRoomId;
    }

    public void setSickRoomId(String sickRoomId) {
        this.sickRoomId = sickRoomId;
    }

    public String getSickRoomOrderId() {
        return sickRoomOrderId;
    }

    public void setSickRoomOrderId(String sickRoomOrderId) {
        this.sickRoomOrderId = sickRoomOrderId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getChangeWard() {
        return changeWard;
    }

    public void setChangeWard(String changeWard) {
        this.changeWard = changeWard;
    }

    public String getSuffererId() {
        return suffererId;
    }

    public void setSuffererId(String suffererId) {
        this.suffererId = suffererId;
    }
}
