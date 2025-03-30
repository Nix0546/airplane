package org.example.dao;

import org.apache.ibatis.annotations.*;
import org.example.entity.Order;
import org.example.entity.Passenger;

import java.util.List;

@Mapper
public interface PassengerMapper {
    @Insert("insert into passenger(name,id_num,user_id) value(#{name},#{idNum},#{userId})")
    int insert(Passenger passenger);
    @Delete("delete from passenger where id=#{param}")
    int delete(Integer id);
    @Insert("insert into orders value(#{param1},#{param2})")
    int order(Integer pid,Integer fid);
    @Delete("delete from orders where passenger_id=#{param1} and flight_id=#{param2}")
    int cancel(Integer pid,Integer fid);
    @Delete("delete from orders where passenger_id=#{param}")
    int cancelById(Integer pid);
    @Select("select * from orders where passenger_id=#{param}")
    List<Order>selectOrder(Integer pid);
    @Select("select * from passenger where id=#{param}")
    Passenger selectById(Integer id);
    @Select("select * from passenger where user_id=#{param}")
    List<Passenger>list(Integer uid);
}
