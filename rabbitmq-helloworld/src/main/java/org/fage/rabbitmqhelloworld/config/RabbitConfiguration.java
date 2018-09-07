package org.fage.rabbitmqhelloworld.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Caizhf
 * @version 1.0
 * @date 上午10:57 2018/8/30
 * @description
 **/
@Component
public class RabbitConfiguration {

    /**
     * 配置一个消息队列
     * @return
     */
    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }
}
