package com.mychatroom.service;

import com.github.pagehelper.Page;
import com.mychatroom.dto.MessageDTO;
import com.mychatroom.dto.WebSocketMessageDTO;
import com.mychatroom.pojo.MessageHistory;

public interface MessageService {
    //添加未读消息
    void addNotReceiveMessage(WebSocketMessageDTO webSocketMessageDTO);

    //查询消息记录--群发消息与个人消息通用查询
    Page<MessageHistory> queryMessageHistory(MessageDTO messageDTO);
}
