package top.ball.rice.hospital.core;

public enum  SickRoomStatusEnum {

    USING("EDITING","使用中"),
    NORMAL("NORMAL","空闲"),
    CLOSED("CLOSED","维修中");

    SickRoomStatusEnum(String code, String desp) {
        this.code = code;
        this.desp = desp;
    }

    SickRoomStatusEnum(String code) {
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
