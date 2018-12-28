package org.fage.rabbitmqnative.component.defaultTest;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Caizhf
 * @version 1.0
 * @date 上午11:21 2018/12/26
 * @description
 **/
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //链接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("rabbitmq_producer");
        factory.setPassword("123");
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("test_vhost");
        //创建连接
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "utf-8");
                System.out.println("消费者消费到消息：" + message);
                //手动告诉rabbit消息确认了
//                this.getChannel().basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume("firstQueue", true, consumer);
    }
}
