package org.example.service;

import org.example.entity.Admin;

import com.github.pagehelper.PageInfo;

public interface AdminService {
    boolean save(Admin admin);
    Admin selectById(Integer id);
    Admin login(String username, String password);
    PageInfo<Admin> page(Integer pageNum, Integer pageSize);
    boolean update(Admin admin);
} 