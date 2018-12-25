package org.fage.userinfoservice;

import org.fage.userinfoservice.controller.UserService;
import org.fage.userinfoservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(UserinfoServiceApplication.class, args);
	}



	@RequestMapping("/hello")
	public User<String> hello(User<String> user){
		userService.addUser();
		User<String> u = new User<String>();
		u.setObj("qweqweqwe");
		return u;
	}
}
