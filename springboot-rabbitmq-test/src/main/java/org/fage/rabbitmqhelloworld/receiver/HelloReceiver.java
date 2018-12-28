package org.fage.rabbitmqhelloworld.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Caizhf
 * @version 1.0
 * @date 上午10:55 2018/8/30
 * @description 监听hello队列的请求
 **/
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello){
        System.out.println("Receiver:" + hello);
    }

}
