package org.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.example.dto.PageDTO;
import org.example.dto.UserInsertDTO;
import org.example.dto.UserLoginDTO;
import org.example.dto.UserUpdateDTO;
import org.example.entity.User;
import org.example.service.UserService;
import org.example.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.bean.BeanUtil;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService service;
    @PostMapping("save")
    public R save(@RequestBody UserInsertDTO dto){
        User user= BeanUtil.copyProperties(dto,User.class);
        if(service.save(user)){
            return R.ok(true);
        }
        return R.error(false);
    }
    @GetMapping("detail/{id}")
    public R detail(@PathVariable Integer id){
        return R.ok(service.selectById(id));
    }

    @PostMapping("login")
    public R login(@RequestBody UserLoginDTO dto){
        User user=service.login(dto.getUsername(),dto.getPassword());
        if(user!=null){
            Map<String,String>claims=new HashMap<>();
            claims.put("id",user.getId().toString());
            claims.put("username",user.getUsername());
            claims.put("identity",user.getIdentity().toString());
            return R.ok(claims);
        }
        return R.error(null);
    }

    @PostMapping("page")
    public R page(@RequestBody PageDTO dto){
        return R.ok(service.page(dto.getPageNum(),dto.getPageSize()));
    }
    
    @PostMapping("update")
    public R update(@RequestBody UserUpdateDTO dto){
        User user = BeanUtil.copyProperties(dto, User.class);
        if(service.update(user)){
            return R.ok(true);
        }
        return R.error(false);
    }
}
