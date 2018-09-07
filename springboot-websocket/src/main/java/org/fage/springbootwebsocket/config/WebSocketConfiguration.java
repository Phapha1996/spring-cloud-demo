package org.fage.springbootwebsocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @author Caizhf
 * @version 1.0
 * @date 下午4:18 2018/9/5
 * @description WebSocket配置
 **/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 允许使用socketJs方式访问，访问点为webSocketServer，允许跨域
        // 在客户端上我们就可以通过这个链接 http://localhost:8080/my_point 来和服务器的WebSocket连接
        registry.addEndpoint("/my_point")
                .setAllowedOrigins("*")
                .withSockJS()
                .setStreamBytesLimit(512 * 1024)
                .setHttpMessageCacheSize(1000)
                .setDisconnectDelay(30 * 1000);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //topic 代表发布广播，即群发
        //queue 代表点对点，即发指定用户
        registry.enableSimpleBroker("/topic","/queue");

        // 全局使用的消息前缀（客户端订阅路径上会体现出来,多加了个/app）
        //registry.setApplicationDestinationPrefixes("/app");

        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        // registry.setUserDestinationPrefix("/user/");
    }


}
