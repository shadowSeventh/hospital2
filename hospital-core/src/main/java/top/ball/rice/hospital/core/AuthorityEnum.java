package top.ball.rice.hospital.core;

public enum AuthorityEnum {

    SUFFERER("患者"),


    DOCTOR("医生"),
    NURSE("护士"),
    STAFF("员工");

    private final String desp;
    private final AuthorityEnum[] children;


    AuthorityEnum(String desp, AuthorityEnum... children) {

        this.desp = desp;
        this.children = children;
    }

    public String getDesp() {
        return desp;
    }

    public AuthorityEnum[] getChildren() {
        return children;
    }
}
