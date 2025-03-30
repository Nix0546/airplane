package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.dao.FlightMapper;
import org.example.dao.PassengerMapper;
import org.example.dto.PassengerCancelDTO;
import org.example.entity.Flight;
import org.example.entity.Order;
import org.example.entity.Passenger;
import org.example.service.FlightService;
import org.example.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    PassengerMapper mapper;
    @Autowired
    FlightMapper flightMapper;

    @Override
    public boolean save(Passenger passenger) {
        return mapper.insert(passenger)>0;
    }

    @Override
    public boolean remove(Integer id) {
        return mapper.delete(id)>0;
    }

    @Override
    public boolean order(Integer pid, Integer fid) {
        Flight flight=flightMapper.selectById(fid);
        int count=flightMapper.selectCount(fid);
        if(count<flight.getNum()){
            return mapper.order(pid,fid)>0;
        }
        return false;
    }

    @Override
    public boolean cancel(Integer pid, Integer fid) {
        return mapper.cancel(pid,fid)>0;
    }

    @Override
    public PageInfo<Passenger> page(Integer pageNum, Integer pageSize, Integer uid) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(mapper.list(uid));
    }

    @Override
    public Passenger getById(Integer id) {
        Passenger passenger=mapper.selectById(id);
        List<Order> orders=mapper.selectOrder(id);
        passenger.setFlights(new LinkedList<>());
        for (Order order : orders) {
            passenger.getFlights().add(flightMapper.selectById(order.getFlightId()));
        }
        return passenger;
    }

    @Override
    public boolean update(PassengerCancelDTO dto) {
        List<Flight> flights=dto.getFlights();
        mapper.cancelById(dto.getId());
        if(flights==null){
            return true;
        }
        for (Flight flight : flights) {
            mapper.order(dto.getId(),flight.getId());
        }
        return true;
    }
}
