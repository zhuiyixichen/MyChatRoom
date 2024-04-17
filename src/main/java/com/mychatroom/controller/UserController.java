package com.mychatroom.controller;


import com.mychatroom.config.Result;
import com.mychatroom.dto.FriendsDTO;
import com.mychatroom.dto.LoginDTO;
import com.mychatroom.dto.WebSocketMessageDTO;
import com.mychatroom.pojo.MessageHistory;
import com.mychatroom.pojo.User;
import com.mychatroom.service.UserService;
import com.mychatroom.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
@Api("用户相关操作")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result<LoginDTO> login(@RequestBody User user){
        log.info("用户登录");
        LoginDTO loginDTO = userService.login(user);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("UserId",loginDTO.getId());
        String token = JwtUtil.createJWT("chen",7200000,claims);
        loginDTO.setToken(token);
        log.info(token);
        return Result.success(loginDTO);
    };

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result register(@RequestBody User user){
        userService.register(user);
        return Result.success();
    }


    @GetMapping("/queryFriends")
    @ApiOperation("查询好友列表")
    public Result<List<FriendsDTO>> queryFriends(Integer userId){
        List<FriendsDTO> list = userService.queryFriends(userId);
        return Result.success(list);
    }

    @GetMapping("/addFriend")
    @ApiOperation("添加好友")
    public Result addFriends(FriendsDTO friendsDTO){
        userService.addFriends(friendsDTO);
        return Result.success("添加成功");
    }

    @DeleteMapping("/deleteFriend")
    @ApiOperation("删除好友")
    public Result deleteFriend(FriendsDTO friendsDTO){
        userService.deleteFriend(friendsDTO);
        return Result.success("删除成功");
    }

    @PutMapping("/updateFriend")
    @ApiOperation("根据ID修改好友相关信息")
    public Result updateFriendById(FriendsDTO friendsDTO){
        userService.updateFriend(friendsDTO);
        return Result.success("修改成功");
    }

    @GetMapping("/queryUserByUserId")
    @ApiOperation("根据用户ID查询用户信息")
    public Result<User> queryUserByUserId(String userId){
        User user = userService.queryUserByUsername(userId);
        return Result.success(user);
    }


}
