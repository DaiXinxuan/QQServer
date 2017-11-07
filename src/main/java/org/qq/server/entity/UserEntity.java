package org.qq.server.entity;


import java.sql.Timestamp;

/**
 * 用户实体，对应数据库中的User表
 */
public class UserEntity {
    //用户ID,主键
    private int userId;
    //用户密码
    private String passwd;
    //用户注册时间
    private Timestamp registerTime;
    //用户上次登录时间
    private Timestamp lastLoginTime;
    //用户是否在线
    private Boolean isOnline;

    public UserEntity() {}

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

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }
}
