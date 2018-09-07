package org.fage.sprigncloudconfigclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@RestController
public class SprigncloudConfigclientApplication {

	@Value("${configlabel}")
	String configLabel;
//    @Autowired
//    private Environment environment;


	public static void main(String[] args) {
		SpringApplication.run(SprigncloudConfigclientApplication.class, args);
	}

	@RequestMapping("/")
	public String getEnv(){
		return configLabel;
	}

}
