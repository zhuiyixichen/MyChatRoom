package com.mychatroom.mapper;

import com.mychatroom.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {
    List<User> queryUserByGroupId(Integer groupId);
}
