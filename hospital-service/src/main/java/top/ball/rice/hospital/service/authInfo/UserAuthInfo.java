package top.ball.rice.hospital.service.authInfo;

import java.io.Serializable;

/**
 * 保存在 session 中 手机号认证信息。
 */
public class UserAuthInfo implements Serializable {

    public static final long serialVersionUID = 1L;

    public static final String SESSION_KEY = UserAuthInfo.class.getName();


    private String userName;
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
