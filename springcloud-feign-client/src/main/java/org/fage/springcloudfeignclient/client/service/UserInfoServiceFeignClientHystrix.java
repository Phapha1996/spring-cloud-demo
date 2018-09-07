package org.fage.springcloudfeignclient.client.service;

import org.fage.springcloudfeignclient.client.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Caizhf
 * @version 1.0
 * @date 下午5:21 2018/7/23
 * @description Hystrix断路器实现类
 **/
@Component
public class UserInfoServiceFeignClientHystrix implements UserInfoServiceFeignClient{
    final Logger logger = LoggerFactory.getLogger(UserInfoServiceFeignClientHystrix.class);
    final String messege = "已经开始服务降级";

    @Override
    public String hello() {
        logger.error("访问hello接口失败" + messege);
        return "访问Hello失败";
    }

    @Override
    public User addUser(User user) {
        logger.error("访问add接口失败" + messege);
        return null;
    }

    @Override
    public String deleteUser(Integer id) {
        logger.error("访问deleteUser接口失败" + messege);
        return "访问deleteUser接口失败";
    }

    @Override
    public User getUser(Integer id) {
        logger.error("访问getUser接口失败" + messege);
        return null;
    }

    @Override
    public List<User> getUserList(Integer pageSize) {
        logger.error("访问getUserList接口失败" + messege);
        return null;
    }
}
