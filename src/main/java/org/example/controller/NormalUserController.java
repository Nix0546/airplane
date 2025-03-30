package org.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.example.dto.NormalUserInsertDTO;
import org.example.dto.NormalUserLoginDTO;
import org.example.dto.NormalUserUpdateDTO;
import org.example.dto.PageDTO;
import org.example.entity.NormalUser;
import org.example.service.NormalUserService;
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
@RequestMapping("/api/v1/normalUser")
public class NormalUserController {
    @Autowired
    NormalUserService service;
    
    @PostMapping("save")
    public R save(@RequestBody NormalUserInsertDTO dto){
        NormalUser user = BeanUtil.copyProperties(dto, NormalUser.class);
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
    public R login(@RequestBody NormalUserLoginDTO dto){
        NormalUser user = service.login(dto.getUsername(), dto.getPassword());
        if(user != null){
            Map<String,Object> userInfo = new HashMap<>();
            userInfo.put("id", user.getId());
            userInfo.put("username", user.getUsername());
            userInfo.put("telephone", user.getTelephone());
            userInfo.put("userType", "normal");
            return R.ok(userInfo);
        }
        return R.error(null);
    }

    @PostMapping("page")
    public R page(@RequestBody PageDTO dto){
        return R.ok(service.page(dto.getPageNum(), dto.getPageSize()));
    }
    
    @PostMapping("update")
    public R update(@RequestBody NormalUserUpdateDTO dto){
        NormalUser user = BeanUtil.copyProperties(dto, NormalUser.class);
        if(service.update(user)){
            return R.ok(true);
        }
        return R.error(false);
    }
} 