package org.fage.rabbitmqnative.component.rpcTest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

/**
 * @author Caizhf
 * @version 1.0
 * @date 上午11:26 2018/12/27
 * @description
 **/
public class RabbitConfig {

    private static ConnectionFactory factory;

    static {
        try {
            factory = new ConnectionFactory();
            factory.setUsername("rabbitmq_producer");
            factory.setPassword("123");
            factory.setHost("127.0.0.1");
            factory.setPort(5672);
            factory.setVirtualHost("test_vhost");
        } catch (Exception e) {
        }
    }


    public static Channel getChannel() {
        try {
            Connection connection = factory.newConnection();
            return connection.createChannel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getConnection(){
        try {
            return factory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void realease(Channel channel){
        if(Objects.nonNull(channel)){
            Connection connection = channel.getConnection();
            try {
                channel.close();
                if(Objects.nonNull(connection)){
                    connection.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

}
