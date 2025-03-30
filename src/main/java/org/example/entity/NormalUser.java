package org.example.entity;

import java.util.List;

import lombok.Data;

@Data
public class NormalUser {
    private Integer id;
    private String username;
    private String password;
    private String telephone;
    private List<Passenger> passengers;
} 