package com.mychatroom.webSocket;

import com.alibaba.fastjson.JSONObject;
import com.mychatroom.config.BeanUtils;
import com.mychatroom.dto.WebSocketMessageDTO;
import com.mychatroom.mapper.GroupMapper;
import com.mychatroom.mapper.UserMapper;
import com.mychatroom.pojo.User;
import com.mychatroom.service.MessageService;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WebSocket聊天相关服务
 */
@Component
@ServerEndpoint("/ws/{userId}")
@Slf4j
//@DependsOn({"messageServiceImpl","userMapper","groupMapper"})
public class WebSocketServer{


    //存放会话对象
    private static Map<Integer, Session> sessionMap = new HashMap();

//    @Autowired
    private MessageService messageService;

//    @Autowired
    private UserMapper userMapper;

//    @Autowired
    private GroupMapper groupMapper;


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Integer userId) {
        log.info("客户端：{}建立连接",userId);
        sessionMap.put(userId, session);
        userMapper = BeanUtils.getBean(UserMapper.class);
        messageService = BeanUtils.getBean(MessageService.class);
        groupMapper = BeanUtils.getBean(GroupMapper.class);

        userMapper.updateUserStatus(userId,1);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, @PathParam("userId") Integer userId) {
        log.info("收到来自客户端：{}的信息: {}",userId,message);
        WebSocketMessageDTO webSocketMessageDTO = JSONObject.parseObject(message, WebSocketMessageDTO.class);
        Integer receiveUserId = webSocketMessageDTO.getReceiveUserId();
            //私发
            if (webSocketMessageDTO.getGroupId() == null){
                sendPrivateClient(webSocketMessageDTO);
            //群发
            }else {
                if(sessionMap.containsKey(receiveUserId)){
                    sendToAllClient(webSocketMessageDTO);
                }
            }
    }

    /**
     * 连接关闭调用的方法
     *
     * @param userId
     */
    @OnClose
    public void onClose(@PathParam("userId") Integer userId) {
        log.info("断开连接：{}",userId);
        sessionMap.remove(userId);
        userMapper.updateUserStatus(userId,0);
    }

    /**
     * 群发
     *
     * @param webSocketMessageDTO
     */
    public void sendToAllClient(WebSocketMessageDTO webSocketMessageDTO) {

        List<User> userList =  groupMapper.queryUserByGroupId(webSocketMessageDTO.getGroupId());
        for (User user : userList) {
            if (user.getStatus() == 1){
                sendPrivateClient(webSocketMessageDTO);
            }else {
                messageService.addNotReceiveMessage(webSocketMessageDTO);
            }
        }
    }

    /**
     * 私发
     * @param webSocketMessageDTO
     */
    public void sendPrivateClient(WebSocketMessageDTO webSocketMessageDTO){
        Integer receiveUserId = webSocketMessageDTO.getReceiveUserId();

        if (sessionMap.containsKey(receiveUserId)){//对方在线
            Session session = sessionMap.get(receiveUserId);
            if (webSocketMessageDTO.getType() == 1){
                try {
                    session.getBasicRemote().sendText(webSocketMessageDTO.getContent());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else if (webSocketMessageDTO.getType() == 2){
                try {
                    session.getBasicRemote().sendText(webSocketMessageDTO.getImageUrl());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }else {// 对方不在线
            //将消息存储到未读消息中
            messageService.addNotReceiveMessage(webSocketMessageDTO);
        }
    }
}
