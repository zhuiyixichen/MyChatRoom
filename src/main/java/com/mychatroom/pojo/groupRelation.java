package com.mychatroom.pojo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 群组关系
 */
@Data
public class groupRelation implements Serializable {
    //ID
    private Integer id;

    //群组ID
    private Integer groupId;

    //用户ID
    private Integer userId;

    //用户姓名备注
    private String remarkName;

    //加入群组时间
    private LocalDateTime joinedTime;

    //是否离开群组
    private Integer ifLeave;

    //是否为管理员
    private Integer ifAdmin;
}
