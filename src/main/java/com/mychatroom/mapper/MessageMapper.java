package com.mychatroom.mapper;

import com.github.pagehelper.Page;
import com.mychatroom.dto.MessageDTO;
import com.mychatroom.dto.WebSocketMessageDTO;
import com.mychatroom.pojo.MessageHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper {

    void insertNotReceiveMessage(WebSocketMessageDTO webSocketMessageDTO);

    Page<MessageHistory> selectFriendMessage(MessageDTO messageDTO);

    Page<MessageHistory> selectGroupMessage(MessageMapper messageMapper);
}
