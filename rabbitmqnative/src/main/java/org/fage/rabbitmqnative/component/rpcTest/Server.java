package org.fage.rabbitmqnative.component.rpcTest;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author Caizhf
 * @version 1.0
 * @date 上午11:36 2018/12/27
 * @description RabbitMQ的RPC调用
 **/
public class Server {
    public static void main(String[] args) throws Exception {
        doRpcClientRequest();
    }

    public static void doRpcClientRequest() throws Exception {
        Channel channel = RabbitConfig.getChannel();
        //执行client请求，从请求队列中拿出数据并且进行处理，然后将处理结果打入响应队列，返回给Client
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //附带发送消息的corID
                System.out.println("请求时的id为：" + properties.getCorrelationId());
                AMQP.BasicProperties props = new  AMQP.BasicProperties.Builder()
                        .correlationId(properties.getCorrelationId())
                        .build();

                //计算阶乘，factorial方法计算阶乘
                int num = Integer.valueOf(new String(body, "UTF-8"));
                int result = factorial(num);
                System.out.println("计算的结果值为:" + result);
                //将计算结果值放入响应队列
                channel.basicPublish(Client.resExchange, Client.resRouteKey, props, String.valueOf(result).getBytes());
            }
        };
        //执行
        channel.basicConsume(Client.reqQueue, true, consumer);
        System.out.println("——————接受客户端请求完成，阶乘计算响应完成——————");
        RabbitConfig.realease(channel);
    }


    /**
     * 计算阶乘
     * @param num
     * @return
     */
    private static int factorial(int num) {
        int result = 1;
        for(int i=num; i>0; i--){
            result *=num;
        }
        return result;
    }

}
