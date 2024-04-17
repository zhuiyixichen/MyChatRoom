package com.mychatroom.dto;

import com.mychatroom.pojo.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户通过长连接发送消息
 */
@Data
public class WebSocketMessageDTO extends User implements Serializable {

    //发送人ID
    private Integer sendUserId;

    //群组ID
    private Integer groupId;

    //接收人ID
    private Integer receiveUserId;

    //文字内容
    private String content;

    //消息类型 1.文字 2.图片
    private Integer type;

    //图片文件
    private MultipartFile image;

    //图片
    private String imageUrl;

    //发送时间
    private LocalDateTime sendTime;
}
