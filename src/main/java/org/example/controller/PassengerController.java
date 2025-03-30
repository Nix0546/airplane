package org.example.controller;

import org.example.dto.PassengerCancelDTO;
import org.example.dto.PassengerInsertDTO;
import org.example.dto.PassengerPageDTO;
import org.example.entity.Passenger;
import org.example.service.PassengerService;
import org.example.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import cn.hutool.core.bean.BeanUtil;

@RestController
@RequestMapping("/api/v1/passenger")
public class PassengerController {
    @Autowired
    PassengerService service;

    @PostMapping("save")
    public R save(@RequestBody PassengerInsertDTO dto){
        Passenger passenger= BeanUtil.copyProperties(dto,Passenger.class);
        if(service.save(passenger)){
            return R.ok(true);
        }
        return R.error(false);
    }
    @GetMapping("remove/{id}")
    public R remove(@PathVariable Integer id){
        if(service.remove(id)){
            return R.ok(true);
        }
        return R.error(false);
    }
    @PostMapping("order/{pid}/{fid}")
    public R order(@PathVariable Integer pid,@PathVariable Integer fid){
        if(service.order(pid,fid)){
            return R.ok(true);
        }
        return R.error(false);
    }
    @PostMapping("cancel")
    public R cancel(@RequestBody PassengerCancelDTO dto){
        return R.ok(service.update(dto));
    }
    @GetMapping("detail/{id}")
    public R detail(@PathVariable Integer id){
        return R.ok(service.getById(id));
    }
    @PostMapping("page")
    public R page(@RequestBody PassengerPageDTO dto){
        Integer uid = dto.getUid();
        System.out.println("查询乘客的用户ID: " + uid);
        if(uid == null || uid <= 0) {
            System.out.println("无效的用户ID");
            return R.error("无效的用户ID");
        }
        
        PageInfo<Passenger> pageInfo = service.page(dto.getPageNum(), dto.getPageSize(), uid);
        System.out.println("查询到乘客数量: " + (pageInfo.getList() != null ? pageInfo.getList().size() : 0));
        
        return R.ok(pageInfo);
    }
}
