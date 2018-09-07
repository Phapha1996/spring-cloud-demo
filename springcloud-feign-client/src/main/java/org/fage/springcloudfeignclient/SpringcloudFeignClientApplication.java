package org.fage.springcloudfeignclient;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * 1、本实例包含feign声明式调用，包含ribbon、Hystrix配置与使用
 * 2、本实例访问的服务端为userinfo-service
 *
 * 3、还有一种学Dubbo的做法，把client接口直接剥离出来，但这种方法饱受热议，如果
 * 不严格遵循开闭原则容易导致并增加团队的开发量
 */
@SpringCloudApplication
@EnableFeignClients
public class SpringcloudFeignClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudFeignClientApplication.class, args);
	}

    /**
     * 设置feign访问日志
     * @return
     */
	@Bean
    Logger.Level feignLoggerLevel(){
	    return Logger.Level.FULL;
    }
}
