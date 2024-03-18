package com.mychatroom.webSocket;

import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

/**
 * WebSocket聊天相关服务
 */
@Component
@ServerEndpoint("/ws")
public class WebSocketServer {

}
