package org.fage.springbootproptest;

import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

/**
 * 本项目演示SpringBoot的多环境配置以及监控与管理/提供Rest访问
 */
@SpringBootApplication
@RestController
public class SpringbootPropTestApplication {
	private static final Logger log = LoggerFactory.getLogger(SpringbootPropTestApplication.class);

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
	public String hello() throws IOException {
		//以下在工程中正常读取
		log.info(this.getClass().getResource("/cert/hello.txt").getPath());
		log.info(ResourceUtils.getURL("classpath:cert/hello.txt").getPath());
		//以下可以读取Jar包文件
		log.info(ClassLoader.getSystemResource("").getPath());
		log.info(Thread.currentThread().getContextClassLoader().getResource("cert/hello.txt").getPath());


		FileInputStream in  = new FileInputStream(this.getClass().getResource("/cert/hello.txt").getPath());
		byte buffer[] = new byte[1024];
		while (-1!=in.read(buffer)){
			String str = new String(buffer);
			log.info(str);
		}
		in.close();
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
