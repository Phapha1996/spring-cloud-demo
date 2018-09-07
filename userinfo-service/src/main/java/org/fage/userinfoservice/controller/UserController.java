package org.fage.userinfoservice.controller;

import org.fage.userinfoservice.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Caizhf
 * @version 1.0
 * @date 上午11:50 2018/7/20
 * @description 提供模拟几个简单的方法给客户端使用
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "hello world";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public User add(@RequestBody User user){
        return user;
    }

    @RequestMapping(value = "/del/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id")Integer id){
        return "delete id=" + id + " User success!";
    }

   @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") Integer id){
        User user = new User();
        user.setId(id);
        user.setUsername("Fage");
        user.setBirthday(new Date(System.currentTimeMillis()));
        return user;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getList(@RequestParam("pageSize") Integer pageSize){
        List<User> users = new ArrayList<>();
        for(int i=1;i<=pageSize;i++){
            User user = new User();
            user.setId(i);
            user.setUsername("user" + i);
            user.setBirthday(new Date(System.currentTimeMillis()));
            users.add(user);
        }
        return users;
    }

}
