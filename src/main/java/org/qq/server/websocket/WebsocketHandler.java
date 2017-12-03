package org.qq.server.websocket;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.qq.server.entity.MessageEntity;
import org.qq.server.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by dxx on 2017/11/13.
 */
@Component
public class WebsocketHandler implements WebSocketHandler {
    @Autowired
    private MessageService messageService;

    //当MyWebSocketHandler类被加载时就会创建该Map，随类而生
    public static final Map<Integer, WebSocketSession> userSocketSessionMap;

    static {
        userSocketSessionMap = new HashMap<Integer, WebSocketSession>();
    }

    //发送信息的实现
    public void sendMessageToUser(int uid, TextMessage message)
            throws IOException {
        WebSocketSession session = userSocketSessionMap.get(uid);
        if (session != null && session.isOpen()) {
            session.sendMessage(message);
        }
    }

    //握手实现连接后
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        int uid = (Integer) webSocketSession.getHandshakeAttributes().get("uid");
        if (userSocketSessionMap.get(uid) == null) {
            userSocketSessionMap.put(uid, webSocketSession);
        }
    }

    //发送信息前的处理
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        if(webSocketMessage.getPayload() == null)
            return;
        //得到Socket通道中的数据并转化为Message对象
        MessageEntity messageEntity = new Gson().fromJson(webSocketMessage.getPayload().toString(),
                MessageEntity.class);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        messageEntity.setSendTime(now);
        messageService.saveMessage(messageEntity);

        sendMessageToUser(messageEntity.getRecvUserId(), new TextMessage(new GsonBuilder().
                setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(messageEntity)));
    }

    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    /**
     * 在此刷新页面就相当于断开WebSocket连接,原本在静态变量userSocketSessionMap中的
     * WebSocketSession会变成关闭状态(close)，但是刷新后的第二次连接服务器创建的
     * 新WebSocketSession(open状态)又不会加入到userSocketSessionMap中,所以这样就无法发送消息
     * 因此应当在关闭连接这个切面增加去除userSocketSessionMap中当前处于close状态的WebSocketSession，
     * 让新创建的WebSocketSession(open状态)可以加入到userSocketSessionMap中
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println("WebSocket:" + webSocketSession.getHandshakeAttributes().get("uid")+" close connection");
        Iterator<Map.Entry<Integer,WebSocketSession>> iterator = userSocketSessionMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer,WebSocketSession> entry = iterator.next();
            if(entry.getValue().getHandshakeAttributes().get("uid") ==
                    webSocketSession.getHandshakeAttributes().get("uid")){
                userSocketSessionMap.remove(webSocketSession.getHandshakeAttributes().get("uid"));
                System.out.println("WebSocket in staticMap:" + webSocketSession.getHandshakeAttributes().get("uid") + "removed");
            }
        }
    }

    public boolean supportsPartialMessages() {
        return false;
    }
}
