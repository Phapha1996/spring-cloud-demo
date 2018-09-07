package org.fage.hystrixribbonclient.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Caizhf
 * @version 1.0
 * @date 上午8:58 2018/7/20
 * @description
 **/
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloConsumer(){
        return restTemplate.getForEntity("http://USERINFO-SERVICE/hello",
                String.class).getBody();
    }

    public String helloFallback(){
        return "需要访问的服务已经宕机，触发了断路器的功能...";
    }
}
