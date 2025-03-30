package org.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.example.dto.AdminLoginDTO;
import org.example.dto.AdminUpdateDTO;
import org.example.dto.PageDTO;
import org.example.entity.Admin;
import org.example.service.AdminService;
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
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    AdminService service;
    
    @GetMapping("detail/{id}")
    public R detail(@PathVariable Integer id){
        return R.ok(service.selectById(id));
    }

    @PostMapping("login")
    public R login(@RequestBody AdminLoginDTO dto){
        Admin admin = service.login(dto.getUsername(), dto.getPassword());
        if(admin != null){
            Map<String,String> claims = new HashMap<>();
            claims.put("id", admin.getId().toString());
            claims.put("username", admin.getUsername());
            claims.put("userType", "admin");
            return R.ok(claims);
        }
        return R.error(null);
    }

    @PostMapping("page")
    public R page(@RequestBody PageDTO dto){
        return R.ok(service.page(dto.getPageNum(), dto.getPageSize()));
    }
    
    @PostMapping("update")
    public R update(@RequestBody AdminUpdateDTO dto){
        Admin admin = BeanUtil.copyProperties(dto, Admin.class);
        if(service.update(admin)){
            return R.ok(true);
        }
        return R.error(false);
    }
} 