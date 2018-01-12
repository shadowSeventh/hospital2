package top.ball.rice.hospital.core;

public enum OrderStatusEnum {

    ORDER("ORDER","已预约"),
    UNPAY("UNPAY","待支付"),
    FINISH("FINISH","已完成"),
    CANCEL("CANCEL","已取消");

    OrderStatusEnum(String code, String desp) {
        this.code = code;
        this.desp = desp;
    }

    OrderStatusEnum(String code) {
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
