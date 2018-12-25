package org.fage.springbootsocketio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootSocketIoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootSocketIoApplication.class, args);
    }
}
