package com.mychatroom.controller;

import com.github.pagehelper.Page;
import com.mychatroom.config.Result;
import com.mychatroom.dto.MessageDTO;
import com.mychatroom.dto.WebSocketMessageDTO;
import com.mychatroom.pojo.MessageHistory;
import com.mychatroom.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
@Api("消息")
@Slf4j
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 根据ID查询消息记录
     * @param MessageDTO
     * @return
     */
    @GetMapping("/queryMessageHistory")
    @ApiOperation("根据ID查询消息记录")
    public Result<Page<MessageHistory>> queryMessageHistoryByUserId(MessageDTO messageDTO){
        Page<MessageHistory> list = messageService.queryMessageHistory(messageDTO);
        return Result.success(list);
    }
}
