package com.mychatroom.pojo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 未接收消息
 */
@Data
public class MessageNotReceived implements Serializable {
    //ID
    private Integer id;

    //发送人
    private Integer sendUserId;

    //接收人
    private Integer receiveUserId;

    //接收群组
    private Integer groupId;

    //消息文字内容
    private String content;

    //消息类型
    private Integer type;

    //图片
    private String imageUrl;

    //发送时间
    private LocalDateTime sendTime;
}
