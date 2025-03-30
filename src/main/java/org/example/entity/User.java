package org.example.entity;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer identity;
    private String telephone;
    private List<Passenger>passengers;
}
