package com.mychatroom.pojo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Friends implements Serializable {
    //ID
    private Integer id;

    //用户名
    private Integer userId;

    //好友用户名
    private Integer friendId;

    //当前关系
    private Integer status;

    //关系建立时间
    private LocalDateTime createTime;

    //关系修改时间
    private LocalDateTime updateTime;

    //添加好友验证消息
    private String appendMsg;

    //好友备注
    private String friendRemarkName;
}
