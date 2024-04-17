package com.mychatroom.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mychatroom.dto.MessageDTO;
import com.mychatroom.dto.WebSocketMessageDTO;
import com.mychatroom.mapper.MessageMapper;
import com.mychatroom.pojo.MessageHistory;
import com.mychatroom.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("messageServiceImpl")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Override
    public void addNotReceiveMessage(WebSocketMessageDTO webSocketMessageDTO) {
        webSocketMessageDTO.setSendTime(LocalDateTime.now());
        messageMapper.insertNotReceiveMessage(webSocketMessageDTO);
    }

    @Override
    public Page<MessageHistory> queryMessageHistory(MessageDTO messageDTO) {
        Page<MessageHistory> list = new Page<>();
        PageHelper.startPage(messageDTO.getPageNumber(), messageDTO.getPageSize());
        if (messageDTO.getReceiveUserId() != null){
            list = messageMapper.selectFriendMessage(messageDTO);
        }else if(messageDTO.getGroupId() != null){
            list = messageMapper.selectGroupMessage(messageMapper);
        }
        return list;
    }
}
