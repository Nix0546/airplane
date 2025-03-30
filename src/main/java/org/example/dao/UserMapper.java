package org.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.entity.User;

@Mapper
public interface UserMapper {
    @Insert("insert into user(username,password,telephone) value(#{username},#{password},#{telephone})")
    int insert(User user);
    @Select("select * from user where id=#{param}")
    User selectById(Integer id);
    @Select("select * from user where username=#{param}")
    User selectByUsername(String username);
    @Select("select * from user where identity=0")
    List<User>list();
    
    @Update("update user set username=#{username}, password=#{password}, telephone=#{telephone} where id=#{id}")
    int update(User user);
}
