package org.fage.hystrixribbonclient.controller;

import org.fage.hystrixribbonclient.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    HelloService helloService;

    @GetMapping("/ribbon-consumer")
    public String helloConsumer(){
        return helloService.helloConsumer();
    }

    @GetMapping("/")
    public String info(){
        return "ComsumerStart..";
    }
}
