package org.fage.springcloudconfigserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 配置中心服务
 * 远程配置访问：/{application}/{profile}/{label} 其中application：配置主名，profile：环境名称，label：分支名称
 *            /{application-profile}.properties
 *            /{label}/{application-profile}.properties
 */
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudConfigserverApplication {

    final static Logger logger = LoggerFactory.getLogger(SpringcloudConfigserverApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudConfigserverApplication.class, args);
	}
}
