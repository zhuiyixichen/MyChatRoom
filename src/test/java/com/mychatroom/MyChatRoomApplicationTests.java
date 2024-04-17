package com.mychatroom;

import com.mychatroom.mapper.GroupMapper;
import com.mychatroom.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

//@SpringBootTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MyChatRoomApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Test
    void test(){
        String file = "sfjna.jpg";
        String[] split = file.split("\\.");
        System.out.println(Arrays.toString(split));
    }
    @Test
    void contextLoads() {
        userMapper.updateUserStatus(1,0);
    }



}
