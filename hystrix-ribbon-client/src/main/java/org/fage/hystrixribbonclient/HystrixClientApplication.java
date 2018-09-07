package org.fage.hystrixribbonclient;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * springcloud断路器测试，访问的是hello-service
 */
//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker   //开启断路器
@SpringCloudApplication //默认开启三个注解
public class HystrixClientApplication {

	@Bean
	@LoadBalanced //负载均衡
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(HystrixClientApplication.class, args);
	}

}
