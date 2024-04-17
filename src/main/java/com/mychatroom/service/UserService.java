package com.mychatroom.service;

import com.mychatroom.dto.FriendsDTO;
import com.mychatroom.dto.LoginDTO;
import com.mychatroom.pojo.User;

import java.util.List;

public interface UserService {

    //用户注册
    void register(User user);

    //用户登录
    LoginDTO login(User user);

    //查询好友列表
    List<FriendsDTO> queryFriends(Integer userId);

    //添加好友
    void addFriends(FriendsDTO friendsDTO);

    //删除好友
    void deleteFriend(FriendsDTO friendsDTO);

    //修改好友相关信息
    void updateFriend(FriendsDTO friendsDTO);

    //根据用户ID查询用户信息
    User queryUserByUsername(String username);
}
