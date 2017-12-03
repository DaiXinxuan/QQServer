package org.qq.server.result;

/**
 * Created by dxx on 2017/11/20.
 */
public class FriendInfo {
    private int friendId;
    private String lastLoginTime;
    private boolean isOnline;

    public FriendInfo() {}

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
