package org.fage.springbootsocketio.config;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.extern.slf4j.Slf4j;
import org.fage.springbootsocketio.bean.TransInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Caizhf
 * @version 1.0
 * @date 2018/9/18 15:28
 * @description 当有交易信息更新时推送更新
 **/
@Component
@Slf4j
public class TransInfoEvenPushHandler {

    @Autowired
    private SocketIOServer socketIoServer;
    private static List<UUID> clientIds = new ArrayList<>();


    /**
     * 客户端连接时触发
     * @param client
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        UUID sessionId = client.getSessionId();
        clientIds.add(sessionId);
        log.info("客户端:{}已成功连接", sessionId);
    }

    /**
     * 客户端关闭时触发
     * @param client
     */
    @OnDisconnect
    public void onDisConnect(SocketIOClient client){
        log.info("客户端:{}断开连接", client.getSessionId());
    }


    /**
     * 客户端推送消息给服务端
     * @param client
     * @param request
     * @param data
     */
    @OnEvent(value = "chatSimpleMessage")
    public void onEvent(SocketIOClient client, AckRequest request, String data) {
        log.info("客户端向服务端主动推送了一条消息:{}", data);
        client.sendEvent("chatSimpleMessage", "服务器已经收到消息,当前时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    /**
     * 消息主动推送
     * @param entity 推送的实体
     * @param <T>
     */
    public <T> void accordSend(T entity){
       clientIds.forEach(clientId -> {
           if (socketIoServer.getClient(clientId) != null) {
               socketIoServer.getClient(clientId).sendEvent("messageevent",  entity);
           }
       });
    }

    @Scheduled(fixedRate = 1000)
    public void test(){
        clientIds.forEach(clientId -> {
            TransInfoEntity transInfoEntity = new TransInfoEntity();
            transInfoEntity.setDataId(new BigDecimal("125209193980960768"));
            transInfoEntity.setTransType(1);
            transInfoEntity.setTransHash("d6cc2eafa2378debf78bc2b0064e000c567fa246da8ebc1f95439b14018e2aae");
            transInfoEntity.setAmount("9999");
            if (socketIoServer.getClient(clientId) != null) {
                socketIoServer.getClient(clientId).sendEvent("entityNew",  transInfoEntity);
            }
        });
    }
}
