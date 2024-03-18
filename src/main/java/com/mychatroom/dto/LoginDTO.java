package com.mychatroom.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class LoginDTO implements Serializable {

    //token
    private String token;
    //ID
    private Integer id;

    //用户名
    private String username;

    //密码
    private String password;

    //昵称
    private String name;

    //头像
    private String avatarUrl;

    //生日
    private Date birthday;

    //个性签名
    private String signature;

    //电话号码
    private long phone;

    //邮箱
    private String email;

    //状态
    private Integer status;

    //最后登录时间
    private LocalDateTime lastLoginTime;

    //创建时间
    private LocalDateTime createTime;
}
