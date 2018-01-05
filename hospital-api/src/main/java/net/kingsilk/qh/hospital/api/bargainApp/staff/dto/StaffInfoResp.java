package net.kingsilk.qh.hospital.api.bargainApp.staff.dto;


public class StaffInfoResp {


    /**
     * 应用的Id。
     */
    private String bargainAppId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 是否禁用
     */
    private Boolean enable;

    /**
     * 备注
     */
    private String memo;

    public String getbargainAppId() {
        return bargainAppId;
    }

    public void setbargainAppId(String bargainAppId) {
        this.bargainAppId = bargainAppId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
