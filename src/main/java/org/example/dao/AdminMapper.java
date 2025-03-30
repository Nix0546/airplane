package org.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.entity.Admin;

@Mapper
public interface AdminMapper {
    @Insert("insert into admin(username,password,telephone) value(#{username},#{password},#{telephone})")
    int insert(Admin admin);
    
    @Select("select * from admin where id=#{param}")
    Admin selectById(Integer id);
    
    @Select("select * from admin where username=#{param}")
    Admin selectByUsername(String username);
    
    @Select("select * from admin")
    List<Admin> list();
    
    @Update("update admin set username=#{username}, password=#{password}, telephone=#{telephone} where id=#{id}")
    int update(Admin admin);
} 