package top.ball.rice.hospital.core;

public enum  SickRoomTypeEnum {

    NORMAL("NORMAL","普通病房"),
    VIP("CLOSED","VIP病房");

    SickRoomTypeEnum(String code, String desp) {
        this.code = code;
        this.desp = desp;
    }

    SickRoomTypeEnum(String code) {
        this(code, null);
    }

    public String getCode() {
        return code;
    }

    public String getDesp() {
        return desp;
    }

    private final String code;
    private final String desp;
}
