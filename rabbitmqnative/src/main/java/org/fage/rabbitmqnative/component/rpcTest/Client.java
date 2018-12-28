package org.fage.rabbitmqnative.component.rpcTest;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Caizhf
 * @version 1.0
 * @date 上午11:36 2018/12/27
 * @description RabbitMQ的RPC调用
 **/
public class Client {

    final static String reqExchange = "reqExchange";
    final static String reqQueue = "reqQueue";
    final static String reqRouteKey = "rpcRequest";
    final static String resExchange = "resExchange";
    final static String resQueue = "resQueue";
    final static String  resRouteKey= "rpcResponse";


    public Client(){
        Channel channel = RabbitConfig.getChannel();
        try {
            System.out.println("初始化交换机、队列中、绑定中...");

            //声明请求交换机、请求队列、绑定
            channel.exchangeDeclare(reqExchange, "direct", true);
            channel.queueDeclare(reqQueue, true, false, false, null);
            channel.queueBind(reqQueue, reqExchange, reqRouteKey);
            //声明响应交换机、响应队列、绑定
            channel.exchangeDeclare(resExchange, "direct", true);
            channel.queueDeclare(resQueue, true, false, false, null);
            channel.queueBind(resQueue, resExchange, resRouteKey);
            //释放资源
            RabbitConfig.realease(channel);

            System.out.println("初始化完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 向服务端发送数字，请求计算num的阶乘
     * @param num
     * @throws IOException
     */
    public String rpcRequest(int num) throws Exception {
        System.out.println("即将调用远程方法执行计算阶乘,将该数字打入队列：" + num);
        //获取通道
        Channel channel = RabbitConfig.getChannel();

        //生成请求的correlationId
        String correlationId = UUID.randomUUID().toString();
        AMQP.BasicProperties props = new AMQP.BasicProperties().builder()
                .correlationId(correlationId)
                .replyTo("resQueue")
                .build();
        System.out.println("请求id为："+correlationId);
        //放入请求队列中
        channel.basicPublish(reqExchange, reqRouteKey, props, String.valueOf(num).getBytes());
        System.out.println("将数字打入队列成功！");
        //释放资源
        RabbitConfig.realease(channel);
        return correlationId;
    }


    /**
     * 获取计算之后返回的阶乘，如果没有结果，会卡住线程
     * @param reqCorrelationId
     * @return
     * @throws Exception
     */
    public void showRpcResponse(String reqCorrelationId) throws Exception {
        System.out.println("等待远程方法响应中,尝试从响应队列获取结果...");
        Channel channel = RabbitConfig.getChannel();
        while (true){
            //消费消息
            boolean autoAsk = false;
            channel.basicConsume(resQueue, autoAsk, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String resCorrelationId = properties.getCorrelationId();
                    System.out.println("从响应队列获取成功！请求时的correlationId为：" + resCorrelationId);
                    long deliveryTag = envelope.getDeliveryTag();
                    //确认消息
                    channel.basicAck(deliveryTag, false);
                    if (reqCorrelationId.equals(resCorrelationId)) {
                        System.out.println("阶乘计算结果：" + Integer.valueOf(new String(body, "UTF-8")));
                    }
                }
            });
        }
    }
}
