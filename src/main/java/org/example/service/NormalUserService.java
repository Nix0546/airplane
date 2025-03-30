package org.example.service;

import org.example.entity.NormalUser;

import com.github.pagehelper.PageInfo;

public interface NormalUserService {
    boolean save(NormalUser user);
    NormalUser selectById(Integer id);
    NormalUser login(String username, String password);
    PageInfo<NormalUser> page(Integer pageNum, Integer pageSize);
    boolean update(NormalUser user);
} 