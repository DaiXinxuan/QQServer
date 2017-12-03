package org.qq.server.dao;

import org.qq.server.entity.MessageEntity;

/**
 * Created by dxx on 2017/11/13.
 */
public interface MessageDao {
    int addMessage(MessageEntity messageEntity);
}
