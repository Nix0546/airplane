package org.example.service;

import org.example.entity.User;

import com.github.pagehelper.PageInfo;


public interface UserService {
    boolean save(User user);
    User selectById(Integer id);
    User login(String username,String password);
    PageInfo<User>page(Integer pageNum,Integer pageSize);
    boolean update(User user);
}
