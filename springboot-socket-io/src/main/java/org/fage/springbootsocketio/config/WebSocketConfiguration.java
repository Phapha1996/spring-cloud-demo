package org.fage.springbootsocketio.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Caizhf
 * @version 1.0
 * @date 2018/9/18 15:15
 * @description
 **/
@Configuration
@Slf4j
public class WebSocketConfiguration implements CommandLineRunner {

    @Autowired
    private SocketIOServer socketIOServer;

    /**
     * 配置socketio服务端
     *
     * @return
     */
    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setPort(8081);
        config.setHostname("localhost");
        return new SocketIOServer(config);
    }

    /**
     * tomcat启动时候，扫描socket服务器并注册
     *
     * @param socketServer
     * @return
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }

    @Override
    public void run(String... strings) throws Exception {
        socketIOServer.start();
        log.info("socket.io启动成功！");
    }
}
