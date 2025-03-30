package org.example.service.impl;

import org.example.dao.NormalUserMapper;
import org.example.dao.PassengerMapper;
import org.example.entity.NormalUser;
import org.example.service.NormalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class NormalUserServiceImpl implements NormalUserService {
    @Autowired
    NormalUserMapper mapper;
    
    @Autowired
    PassengerMapper passengerMapper;

    @Override
    public boolean save(NormalUser user) {
        return mapper.insert(user) > 0;
    }

    @Override
    public NormalUser selectById(Integer id) {
        NormalUser user = mapper.selectById(id);
        user.setPassengers(passengerMapper.list(id));
        return user;
    }

    @Override
    public NormalUser login(String username, String password) {
        NormalUser user = mapper.selectByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            user.setPassengers(passengerMapper.list(user.getId()));
            return user;
        }
        return null;
    }

    @Override
    public PageInfo<NormalUser> page(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(mapper.list());
    }

    @Override
    public boolean update(NormalUser user) {
        return mapper.update(user) > 0;
    }
} 