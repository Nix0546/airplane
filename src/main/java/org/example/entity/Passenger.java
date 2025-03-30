package org.example.entity;

import lombok.Data;

import java.util.List;

@Data
public class Passenger {
    private Integer id;
    private String name;
    private String idNum;
    private Integer userId;
    private List<Flight> flights;
}
