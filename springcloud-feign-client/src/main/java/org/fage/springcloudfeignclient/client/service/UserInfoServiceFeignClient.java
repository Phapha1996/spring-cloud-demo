package org.fage.springcloudfeignclient.client.service;

import org.fage.springcloudfeignclient.client.bean.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Caizhf
 * @version 1.0
 * @date 下午2:38 2018/7/20
 * @description 访问服务提供者的客户端
 **/
@FeignClient(value = "USERINFO-SERVICE", fallback = UserInfoServiceFeignClientHystrix.class)
public interface UserInfoServiceFeignClient {

    @RequestMapping(value = "/user/hello", method = RequestMethod.GET)
    String hello();

    @RequestMapping(value = "/user/new", method = RequestMethod.POST)
    User addUser(@RequestBody User user);

    @RequestMapping(value = "/user/del/{id}", method = RequestMethod.GET)
    String deleteUser(@PathVariable("id") Integer id);

    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
    User getUser(@PathVariable("id") Integer id);

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    List<User> getUserList(@RequestParam("pageSize") Integer pageSize);
}
