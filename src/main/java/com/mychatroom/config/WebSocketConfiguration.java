package com.mychatroom.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 *webSocket相关配置，用于注册webSocket的bean
 */
@Configuration
@Slf4j
public class WebSocketConfiguration {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        log.info("开始注册WebSocket连接器");
        return new ServerEndpointExporter();
    }
}
