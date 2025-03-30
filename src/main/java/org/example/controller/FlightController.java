package org.example.controller;

import cn.hutool.core.bean.BeanUtil;
import org.example.dto.FlightInsertDTO;
import org.example.dto.FlightUpdateDTO;
import org.example.dto.PageDTO;
import org.example.entity.Flight;
import org.example.service.FlightService;
import org.example.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/flight")
public class FlightController {
    @Autowired
    FlightService service;

    @PostMapping("save")
    public R save(@RequestBody FlightInsertDTO dto){
        Flight flight= BeanUtil.copyProperties(dto,Flight.class);
        if(service.save(flight)){
            return R.ok(true);
        }
        return R.error(false);
    }
    @PostMapping("update")
    public R update(@RequestBody FlightUpdateDTO dto){
        Flight flight= BeanUtil.copyProperties(dto,Flight.class);
        if(service.update(flight)){
            return R.ok(true);
        }
        return R.error(false);
    }

    @PostMapping("remove/{id}")
    public R remove(@PathVariable Integer id){
        if(service.remove(id)){
            return R.ok(true);
        }
        return R.error(false);
    }
    @GetMapping("detail/{id}")
    public R detail(@PathVariable Integer id){
        return R.ok(service.getById(id));
    }

    @PostMapping("page")
    public R page(@RequestBody PageDTO dto){
        return R.ok(service.page(dto.getPageNum(),dto.getPageSize()));
    }
}
