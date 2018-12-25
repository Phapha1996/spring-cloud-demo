package org.fage.querydsltest;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.fage.querydsltest.bean.UserDTO;
import org.fage.querydsltest.entity.User;
import org.fage.querydsltest.repository.UserRepository;
import org.fage.querydsltest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QueryDslTestApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    public void contextLoads() {
        User u1 = userService.findByUsernameAndPassword("fafa", "123");
        log.info(u1==null ? null :u1.toString());

        List<User> users1 = userService.findAll();
        log.info(users1==null ? null :users1.toString());

//        List<UserDTO> dtoList = userService.findAllUserDto();
//        log.info(dtoList.toString());

        List<User> users2 = userService.findByNicknameAndUsername("fafa", "fafa");
        log.info(users2.toString());

        long likeNameCount = userService.countByNickNameLike("fa");
        log.info(likeNameCount + "");

//        Page<User> page1 = userService.findByUserProperties("fafa", "123", null, null, null);
//        log.info(page1.toString());
        List<User> userPage = userService.findByUserPropertiesGroupByUIndex(null,null,null,null,null);
        log.info(JSONObject.toJSONString(userPage));
        Page<User> page = userRepository.findAll(new PageRequest(0,10));
        log.info(JSONObject.toJSONString(page));
    }

    @Test
    public void test(){

        log.info(userService.findByDepatmentIdDTO(1).toString());
    }

}
