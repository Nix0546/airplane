package org.example.dto;

import lombok.Data;

@Data
public class NormalUserUpdateDTO {
    private Integer id;
    private String username;
    private String password;
    private String telephone;
} 