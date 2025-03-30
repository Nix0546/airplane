package org.example.service.impl;

import org.example.dao.AdminMapper;
import org.example.entity.Admin;
import org.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper mapper;

    @Override
    public boolean save(Admin admin) {
        return mapper.insert(admin) > 0;
    }

    @Override
    public Admin selectById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public Admin login(String username, String password) {
        Admin admin = mapper.selectByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }

    @Override
    public PageInfo<Admin> page(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(mapper.list());
    }

    @Override
    public boolean update(Admin admin) {
        return mapper.update(admin) > 0;
    }
} 