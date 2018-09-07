package org.fage.springbootproptest;

import entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 本项目演示SpringBoot的多环境配置以及监控与管理/提供Rest访问
 */
@SpringBootApplication
@RestController
public class SpringbootPropTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootPropTestApplication.class, args);
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		JettyEmbeddedServletContainerFactory factory =
				new JettyEmbeddedServletContainerFactory();
		return factory;
	}

	@RequestMapping("/hello")
	public String hello(){
		return "helloWorld!";
	}

	@PostMapping("/selfStr")
	public String getSelfStr(@RequestParam(name = "message") String message){
		return message;
	}

	@GetMapping("/user/{id}")
    public User getUser(@PathVariable int id){
	    User user = new User();
	    user.setId(id);
	    user.setUsername("fafa");
	    user.setBirthday(new Date());
	    return user;
    }

    @PostMapping("/user/new")
    public User newUser(@RequestBody User user){
	    return user;
    }

}
