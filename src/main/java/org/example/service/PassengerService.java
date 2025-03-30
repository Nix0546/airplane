package org.example.service;


import com.github.pagehelper.PageInfo;
import org.example.dto.PassengerCancelDTO;
import org.example.entity.Passenger;

import java.util.List;

public interface PassengerService {
    boolean save(Passenger passenger);
    boolean remove(Integer id);
    boolean order(Integer pid,Integer fid);
    boolean cancel(Integer pid,Integer fid);
    PageInfo<Passenger>page(Integer pageNum,Integer pageSize,Integer uid);
    Passenger getById(Integer id);
    boolean update(PassengerCancelDTO dto);
}
