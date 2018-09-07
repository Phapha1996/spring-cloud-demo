package org.fage.springbootwebsocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.fage.springbootwebsocket.bean.RequestMessage;
import org.fage.springbootwebsocket.bean.ReponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * @author Caizhf
 * @version 1.0
 * @date 下午4:22 2018/9/5
 * @description 测试websocket异步双工通信
 **/
@RestController
@Slf4j
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private static int count = 0;

    /*@MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ReponseMessage say(@Payload RequestMessage message) throws Exception {
        //等待3秒返回消息内容
        Thread.sleep(3000);
        return new ReponseMessage("欢迎使用webScoket：" + message.getName());
    }*/

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ReponseMessage say(Message message,
                              MessageHeaders MessageHeaders,
                              @Header("destination") String destination,
                              @Headers Map<String, Object> headers,
//                              @DestinationVariable long id,
                              @Payload String body) throws Exception {

        log.info("[test] Message: {}", message);
        log.info("[test] MessageHeaders: {}", MessageHeaders);
        log.info("[test] Header: {}", destination);
        log.info("[test] Headers: {}", headers);
//        log.info("[test] DestinationVariable: {}", id);
        log.info("[test] Payload: {}", body);
        //等待3秒返回消息内容
        Thread.sleep(3000);
        return new ReponseMessage("欢迎使用webScoket");
    }

    /**
     * 服务器主动推送
     * 每隔一秒钟向客户端推送一次消息
     *
     * @return
     * @throws InterruptedException
     */
    @Scheduled(fixedRate = 1000)
    @SendTo("/topic/getResponse")
    public Object sayLoop() throws InterruptedException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messagingTemplate.convertAndSend("/topic/getResponse", new ReponseMessage("你好,这里是websocket自动推送~,当前第"+ ++count +"条推送数据,服务器时间:" + df.format(new Date())));
        return "callback";
    }
}
