package org.fage.springcloudfeignclient.controller;

import org.fage.springcloudfeignclient.client.bean.User;
import org.fage.springcloudfeignclient.client.service.UserInfoServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author Caizhf
 * @version 1.0
 * @date 上午11:47 2018/7/12
 * @description 消费者接口
 **/
@RestController
@RequestMapping("/")
public class UserInfoComsumerController {

    @Autowired
    UserInfoServiceFeignClient userInfoServiceFeignClient;

    @GetMapping("/hello")
    public String helloWorld(){
        return userInfoServiceFeignClient.hello();
    }

    @GetMapping("/newUser")
    public User addUser(){
        User user = new User();
        user.setId(1);
        user.setUsername("小蔡");
        user.setBirthday(new Date(System.currentTimeMillis()));
        return userInfoServiceFeignClient.addUser(user);
    }

    @GetMapping("/delUser/{id}")
    public String delete(@PathVariable("id")Integer id){
        System.out.println(id);
       return userInfoServiceFeignClient.deleteUser(id);
    }


    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable int id){
        return userInfoServiceFeignClient.getUser(id);
    }

    @GetMapping("/getUserList/{pageSize}")
    public List<User> getUserList(@PathVariable int pageSize){
        return userInfoServiceFeignClient.getUserList(pageSize);
    }


}
