package com.mychatroom.dto;

import com.mychatroom.pojo.MessageHistory;
import lombok.Data;

@Data
public class MessageDTO extends MessageHistory {
    //页码
    private Integer pageNumber;
    //每页展示数量
    private Integer pageSize;
}
