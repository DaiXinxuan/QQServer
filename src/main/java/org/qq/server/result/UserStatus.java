package org.qq.server.result;


/**
 * Created by dxx on 2017/11/10.
 * 用户返回给用户的查找用户状态类
 */
public class UserStatus {
    private int userId;
    private Boolean isOnline;
    private String lastLoginTime;

    public UserStatus() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
