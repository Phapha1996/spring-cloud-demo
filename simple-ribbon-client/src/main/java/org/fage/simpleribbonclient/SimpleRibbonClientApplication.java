package org.fage.simpleribbonclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * springcloud第一个ribbon消费者服务项目
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SimpleRibbonClientApplication {

	@Bean
	@LoadBalanced //负载均衡
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(SimpleRibbonClientApplication.class, args);
	}

}
