package top.ball.rice.hospital.core;

public enum ArticleStatusEnum {
    EDITING("EDITING", "编辑中"),
    NORMAL("NORMAL", "发布中"),
    CLOSED("CLOSED", "已关闭");

    ArticleStatusEnum(String code, String desp) {
        this.code = code;
        this.desp = desp;
    }

    ArticleStatusEnum(String code) {
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
