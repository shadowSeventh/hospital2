package top.ball.rice.hospital.domain;

import top.ball.rice.hospital.core.SickRoomStatusEnum;
import top.ball.rice.hospital.core.SickRoomTypeEnum;

import javax.persistence.Entity;

@Entity
public class SickRoom extends Base {

    /**
     * 序号
     */
    private String Seq;

    /**
     * 病房类型
     */
    private SickRoomTypeEnum type;

    /**
     * 状态
     */
    private SickRoomStatusEnum status;

    public String getSeq() {
        return Seq;
    }

    public void setSeq(String seq) {
        Seq = seq;
    }

    public SickRoomTypeEnum getType() {
        return type;
    }

    public void setType(SickRoomTypeEnum type) {
        this.type = type;
    }

    public SickRoomStatusEnum getStatus() {
        return status;
    }

    public void setStatus(SickRoomStatusEnum status) {
        this.status = status;
    }
}
