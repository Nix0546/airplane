package org.example.service.impl;

import org.example.dao.PassengerMapper;
import org.example.dao.UserMapper;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService  {
    @Autowired
    UserMapper mapper;
    @Autowired
    PassengerMapper passengerMapper;

    @Override
    public boolean save(User user) {
        return mapper.insert(user)>0;
    }

    @Override
    public User selectById(Integer id) {
        User user=mapper.selectById(id);
        user.setPassengers(passengerMapper.list(id));
        return user;
    }

    @Override
    public User login(String username, String password) {
        User user=mapper.selectByUsername(username);
        if(user!=null&&user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    @Override
    public PageInfo<User> page(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(mapper.list());
    }
    
    @Override
    public boolean update(User user) {
        return mapper.update(user) > 0;
    }
}
