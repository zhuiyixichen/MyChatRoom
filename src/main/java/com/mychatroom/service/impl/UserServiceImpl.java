package com.mychatroom.service.impl;

import com.mychatroom.customException.UsernameExistException;
import com.mychatroom.customException.UsernameOrPasswordWrongException;
import com.mychatroom.dto.FriendsDTO;
import com.mychatroom.dto.LoginDTO;
import com.mychatroom.mapper.UserMapper;
import com.mychatroom.pojo.User;
import com.mychatroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 用户注册
     * @param user
     */
    @Override
    public void register(User user) {
        String username = userMapper.queryUserIsExist(user.getUsername());
        if (username != null){
            //用户名已存在
            throw new UsernameExistException("该用户名已存在");
        }
            String password = user.getPassword();
        String pwd = null;
        try {
            pwd = md5(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        user.setPassword(pwd);

        userMapper.insertUser(user);
    }

    //md5加密
    public String md5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(password.getBytes());
        BigInteger bigInteger = new BigInteger(1, digest);
        String pwd = bigInteger.toString(16);
        return pwd;
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public LoginDTO login(User user) {
        User queryUser = userMapper.queryPasswordByUsername(user.getUsername());
        try {
            String pwd = md5(user.getPassword());
            if(queryUser.getPassword().equals(pwd)){
                LoginDTO loginDTO = userMapper.queryById(queryUser.getId());
                return loginDTO;
            }else{
                //TODO 新增异常
                throw new UsernameOrPasswordWrongException("用户名或密码错误");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 查询好友列表
     * @param userId
     * @return
     */
    @Override
    public List<FriendsDTO> queryFriends(Integer userId) {
        List<FriendsDTO> list = userMapper.selectFriends(userId);
        return list;
    }

    @Override
    public void addFriends(FriendsDTO friendsDTO) {
        friendsDTO.setStatus(2);
        friendsDTO.setCreateTime(LocalDateTime.now());
        userMapper.insertFriend(friendsDTO);
    }

    @Override
    public void deleteFriend(FriendsDTO friendsDTO) {
        friendsDTO.setUpdateTime(LocalDateTime.now());
        userMapper.deleteFriend(friendsDTO);
    }

    @Override
    public void updateFriend(FriendsDTO friendsDTO) {
        friendsDTO.setUpdateTime(LocalDateTime.now());
        userMapper.updateFriend(friendsDTO);
    }
}
