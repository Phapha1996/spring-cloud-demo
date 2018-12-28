package org.fage.rabbitmqnative.component.rpcTest;

/**
 * @author Caizhf
 * @version 1.0
 * @date 上午11:37 2018/12/27
 * @description
 **/
public class ClientApp {
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        //求10的阶乘，执行远程请求
//        String reqId = client.rpcRequest(10);
        client.showRpcResponse("98bf4134-6a89-4595-901a-e5e13de0f5e0");
    }
}
