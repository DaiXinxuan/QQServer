package org.qq.server.service.Impl;

import org.qq.server.dao.MessageDao;
import org.qq.server.entity.MessageEntity;
import org.qq.server.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dxx on 2017/11/13.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    public int saveMessage(MessageEntity messageEntity) {
        return messageDao.addMessage(messageEntity);
    }
}
