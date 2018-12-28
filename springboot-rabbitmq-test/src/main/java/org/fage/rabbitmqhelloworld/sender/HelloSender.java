package org.fage.rabbitmqhelloworld.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Caizhf
 * @version 1.0
 * @date 上午10:49 2018/8/30
 * @description 消息发送方
 **/
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送到名为hello的队列中
     */
    public void send(){
        String context = "hello! this is a simple message" + new Date();
        System.out.println("Sender:" + context);
        this.amqpTemplate.convertAndSend("hello", context);
    }

}
