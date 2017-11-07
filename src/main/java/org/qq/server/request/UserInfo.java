package org.qq.server.request;

/**
 * Created by dxx on 2017/11/7.
 */
public class UserInfo {
    private int userId;
    private String passwd;

    public UserInfo() {}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
