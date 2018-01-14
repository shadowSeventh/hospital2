package top.ball.rice.hospital.core;

public enum StaffStatusEnum {

    WORKING("WORKING", "工作中"),
    VACATION("VACATION", "休假中"),
    QUIT("QUIT", "已离职");

    StaffStatusEnum(String code, String desp) {
        this.code = code;
        this.desp = desp;
    }

    StaffStatusEnum(String code) {
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
