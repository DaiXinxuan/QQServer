package org.qq.server.entity;

import java.sql.Timestamp;

/**
 * Created by dxx on 2017/11/10.
 */
public class FriendshipEntity {
    //好友关系Id，主键，自动生成
    private int friendshipId;
    //好友关系中第一个用户的用户Id
    private int fUserId;
    //好友关系中第二个用户的用户Id
    private int sUserId;
    //好友关系建立的时间
    private Timestamp setUpTime;

    public FriendshipEntity() {}

    public int getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(int friendshipId) {
        this.friendshipId = friendshipId;
    }

    public int getfUserId() {
        return fUserId;
    }

    public void setfUserId(int fUserId) {
        this.fUserId = fUserId;
    }

    public int getsUserId() {
        return sUserId;
    }

    public void setsUserId(int sUserId) {
        this.sUserId = sUserId;
    }

    public Timestamp getSetUpTime() {
        return setUpTime;
    }

    public void setSetUpTime(Timestamp setUpTime) {
        this.setUpTime = setUpTime;
    }
}
