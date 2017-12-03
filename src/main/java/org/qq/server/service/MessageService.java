package org.qq.server.service;

import org.qq.server.entity.MessageEntity;

/**
 * Created by dxx on 2017/11/13.
 */
public interface MessageService {
    int saveMessage(MessageEntity messageEntity);
}
