package org.fage.userinfoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * springcloud第一个服务项目
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RequestMapping("/")
public class UserinfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserinfoServiceApplication.class, args);
	}

	@RequestMapping("/hello")
	public String hello(){
		return "hello~ I am userinfoService!!";
	}
}
