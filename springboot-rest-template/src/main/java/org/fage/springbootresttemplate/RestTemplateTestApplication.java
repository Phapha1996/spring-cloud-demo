package org.fage.springbootresttemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Caizhf
 * @version 1.0
 * @date 下午3:06 2018/7/12
 * @description
 **/
@SpringBootApplication
public class RestTemplateTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestTemplateTestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
