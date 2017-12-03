package org.qq.server.entity;

import java.sql.Timestamp;

/**
 * Created by dxx on 2017/11/13.
 */
public class MessageEntity {
    private int messageId;

    private String msg;

    private int sendUserId;

    private int recvUserId;

    private Timestamp sendTime;

    public MessageEntity() {}

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(int sendUserId) {
        this.sendUserId = sendUserId;
    }

    public int getRecvUserId() {
        return recvUserId;
    }

    public void setRecvUserId(int recvUserId) {
        this.recvUserId = recvUserId;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }
}
