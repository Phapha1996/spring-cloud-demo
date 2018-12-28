package org.fage.rabbitmqnative.component.defaultTest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Caizhf
 * @version 1.0
 * @date 上午11:16 2018/12/26
 * @description 使用默认队列
 **/
public class Producer {
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
        String queueName = "firstQueue";
        channel.queueDeclare(queueName, true, false, false, null);
        String message = "这是测试默认交换机rabbitMQ的程序";
        channel.basicPublish("", queueName, null, message.getBytes());
        channel.close();
        connection.close();
    }
}
