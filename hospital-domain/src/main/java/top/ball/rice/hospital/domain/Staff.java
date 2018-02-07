package top.ball.rice.hospital.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * 员工表
 */
@Entity
public class Staff extends Base {

    private String userId;
    /**
     * 是否已经禁用。
     * <p>
     * true - 已经禁用。
     */
    private boolean disabled;

    /**
     * 备注
     */
    private String memo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
