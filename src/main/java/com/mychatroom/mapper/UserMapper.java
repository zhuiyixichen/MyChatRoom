package com.mychatroom.mapper;

import com.mychatroom.dto.FriendsDTO;
import com.mychatroom.dto.LoginDTO;
import com.mychatroom.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper{

    @Insert("insert into user (username, password, name, avatar_url, birthday, signature, phone, email, status, last_login_time, create_time)" +
            "VALUES (#{username},#{password},#{name},#{avatarUrl},#{birthday},#{signature},#{phone},#{email},#{status},#{lastLoginTime},#{createTime})")
    void insertUser(User user);

    @Select("select id,password from user where username = #{username}")
    User queryPasswordByUsername(String username);

    @Select("select * from user where id = #{id}")
    LoginDTO queryById(Integer id);


    @Select("select username from user where username = #{username}")
    String queryUserIsExist(String username);


    List<FriendsDTO> selectFriends(Integer userId);

    @Insert("insert into friends (user_id, friend_id, status, create_time, update_time, append_msg, friend_remark_name) VALUES " +
            "(#{user.id},#{friendId},#{status},#{createTime},#{updateTime},#{appendMsg},#{friendRemarkName})")
    void insertFriend(FriendsDTO friendsDTO);

    void deleteFriend(FriendsDTO dto);

    void updateFriend(FriendsDTO friendsDTO);

    void updateUserStatus(@Param("userId") Integer userId,@Param("status") Integer status);

    @Select("select * from user where id = #{userId}")
    User selectUserByUsername(String userId);
}
