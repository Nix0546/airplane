package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.dao.FlightMapper;
import org.example.entity.Flight;
import org.example.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    FlightMapper mapper;

    @Override
    public boolean save(Flight flight) {
        return mapper.insert(flight)>0;
    }

    @Override
    public boolean update(Flight flight) {
        return mapper.updateById(flight)>0;
    }

    @Override
    public boolean remove(Integer id) {
        return mapper.deleteById(id)>0;
    }

    @Override
    public Flight getById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public PageInfo<Flight> page(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(mapper.list());
    }
}
