package org.fage.simpleribbonclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Caizhf
 * @version 1.0
 * @date 上午11:47 2018/7/12
 * @description
 **/
@RestController
@RequestMapping("/")
public class ComsumerController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/ribbon-consumer")
    public String helloConsumer(){
        return restTemplate.getForEntity("http://USERINFO-SERVICE/hello",
        String.class).getBody();
    }

    @GetMapping("/")
    public String info(){
        return "ComsumerStart..";
    }
}
