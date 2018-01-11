package top.ball.rice.hospital.server.common;

/**
 * 参考 spring-data 中的 Page.Order，方便 Jackson 实例化。
 */
public class UniOrder {

    private String property;

    private boolean desc;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }

}
