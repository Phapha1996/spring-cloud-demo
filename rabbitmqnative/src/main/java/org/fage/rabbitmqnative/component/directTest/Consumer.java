package org.fage.rabbitmqnative.component.directTest;

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
 * @date 上午11:32 2018/12/25
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
        //获取信道
        final Channel channel = connection.createChannel();
        String exchangeName = "exchange_fafa";
        String queueName = "queue_fafa";
        String routeKey = "bind_fafa";
        //声明Exchange
//        channel.exchangeDeclare(exchangeName, "direct", true);
        //绑定队列
//        channel.queueBind(queueName, exchangeName, routeKey);

        while (true){
            //消费消息
            boolean autoAsk = false;
            String consumerTage = "";
            channel.basicConsume(queueName, autoAsk, consumerTage, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String routingKey = envelope.getRoutingKey();
                    String contentType = properties.getContentType();
                    System.out.println("消费的路由键：" + routingKey);
                    System.out.println("消费内容的类型：" + contentType);
                    long deliveryTag = envelope.getDeliveryTag();
                    //确认消息
                    channel.basicAck(deliveryTag, false);
                    System.out.println("消费的消息体内容：");
                    String bodyStr = new String(body, "UTF-8");
                    System.out.println(bodyStr);
                }
            });
        }

    }
}
