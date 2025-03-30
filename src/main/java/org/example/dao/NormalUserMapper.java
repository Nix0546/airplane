package org.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.entity.NormalUser;

@Mapper
public interface NormalUserMapper {
    @Insert("insert into normal_user(username,password,telephone) value(#{username},#{password},#{telephone})")
    int insert(NormalUser user);
    
    @Select("select * from normal_user where id=#{param}")
    NormalUser selectById(Integer id);
    
    @Select("select * from normal_user where username=#{param}")
    NormalUser selectByUsername(String username);
    
    @Select("select * from normal_user")
    List<NormalUser> list();
    
    @Update("update normal_user set username=#{username}, password=#{password}, telephone=#{telephone} where id=#{id}")
    int update(NormalUser user);
} 