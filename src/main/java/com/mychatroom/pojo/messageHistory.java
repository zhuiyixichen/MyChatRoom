package com.mychatroom.pojo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 历史消息
 */
@Data
public class messageHistory implements Serializable {
    //ID
    private Integer id;

    //发送人ID
    private Integer sendUserId;

    //群组ID
    private Integer groupId;

    //接收人ID
    private Integer receiveUserId;

    //文字内容
    private String content;

    //消息类型
    private Integer type;

    //图片
    private String imageUrl;

    //发送时间
    private LocalDateTime sendTime;
}
