package com.mychatroom.pojo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Group implements Serializable {
    //ID
    private Integer id;

    //群组名称
    private String groupName;

    //群组创建人
    private Integer createUserId;

    //群组信息简介
    private String description;

    //群组创建时间
    private LocalDateTime createTime;

}
