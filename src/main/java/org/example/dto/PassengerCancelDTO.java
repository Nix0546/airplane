package org.example.dto;

import lombok.Data;
import org.example.entity.Flight;

import java.util.List;

@Data
public class PassengerCancelDTO {
    private Integer id;
    private List<Flight> flights;
}
