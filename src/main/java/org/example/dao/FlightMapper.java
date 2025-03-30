package org.example.dao;

import lombok.Data;
import org.apache.ibatis.annotations.*;
import org.example.entity.Flight;

import java.util.List;

@Mapper
public interface FlightMapper {
    @Insert("insert into flight(source,target,time,num) value(#{source},#{target},#{time},#{num})")
    int insert(Flight flight);
    @Update("update flight set source=#{source},target=#{target},time=#{time},num=#{num} where id=#{id}")
    int updateById(Flight flight);
    @Delete("delete from flight where id=#{param}")
    int deleteById(Integer id);
    @Select("select * from flight where id=#{param}")
    Flight selectById(Integer id);
    @Select("select * from flight")
    List<Flight> list();
    @Select("select count(*) from orders where flight_id=#{param}")
    int selectCount(Integer fid);
}
