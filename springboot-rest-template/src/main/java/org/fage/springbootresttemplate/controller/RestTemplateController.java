package org.fage.springbootresttemplate.controller;

import org.fage.springbootresttemplate.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @author Caizhf
 * @version 1.0
 * @date 下午3:08 2018/7/12
 * @description rest学习博客：https://blog.csdn.net/itguangit/article/details/78825505
 **/
@RestController
public class RestTemplateController {

    @Autowired
    RestTemplate restTemplate;
    Logger logger = LoggerFactory.getLogger(RestTemplate.class);

    final String url = "http://127.0.0.1:9000/user";

    /**
     * 测试get方法
     * @param id
     * @return
     */
    @GetMapping("/getForRest/{id}")
    public User getUserForRest(@PathVariable int id){
        //就像查询一样的get请求
        ResponseEntity<User> responseEntity = restTemplate.getForEntity(url + "/{"+id+"}", User.class, id);
        logger.info(responseEntity.getBody().toString());
        logger.info(responseEntity.getStatusCodeValue() + "");

        return responseEntity.getBody();
    }


    @GetMapping("/postForRest")
    public User postDataForRest(){
        User user = new User();
        user.setId(1);
        user.setUsername("Caizhfy");
        user.setBirthday(new Date());
        //就像注册一样的post请求
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(url + "/new", user, User.class);

        return responseEntity.getBody();
    }
}
