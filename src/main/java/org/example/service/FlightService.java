package org.example.service;

import com.github.pagehelper.PageInfo;
import org.example.entity.Flight;

public interface FlightService {
    boolean save(Flight flight);
    boolean update(Flight flight);
    boolean remove(Integer id);
    Flight getById(Integer id);
    PageInfo<Flight> page(Integer pageNum,Integer pageSize);
}
