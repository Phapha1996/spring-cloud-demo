package org.fage.rabbitmqnative.component.directTest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Caizhf
 * @version 1.0
 * @date 上午11:15 2018/12/25
 * @description 生产者
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
        //获取信道
        Channel channel = connection.createChannel();
        String exchangeName = "exchange_fafa";
        String queueName = "queue_fafa";
        String routeKey = "bind_fafa";
        String message = "你好rabbit，这是我想要发送的信息~你好吗";
        //声明交换机
        channel.exchangeDeclare(exchangeName, "direct", true);
        //声明队列
        channel.queueDeclare(queueName, true, false, false, null);
        //绑定交换机与队列的绑定，路由关键字
        channel.queueBind(queueName, exchangeName, routeKey);
        //发布消息
        byte[] messageBody = message.getBytes();
        channel.basicPublish(exchangeName, routeKey, null, messageBody);
        System.out.println("将以下信息打入队列中：" + message);
        channel.close();
        connection.close();
    }
}
