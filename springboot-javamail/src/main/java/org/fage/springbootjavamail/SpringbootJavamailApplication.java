package org.fage.springbootjavamail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

/**
 * Java邮件系统
 *
 */
@SpringBootApplication
@RestController
@Slf4j
public class SpringbootJavamailApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJavamailApplication.class, args);
	}

	/**
	 * 测试获取静态资源
	 * @return
	 * @throws FileNotFoundException
	 */
	@RequestMapping("/")
	public String getPath() throws FileNotFoundException {
		String classPath = ResourceUtils.getURL("classpath:").getPath().toString();
		log.info(classPath);
		return classPath;
	}
}
