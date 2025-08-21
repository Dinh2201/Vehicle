package com.example.vehicle.dtos.response.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTypeDTO {

    private Long vehicleTypeId;
    private String name;
    private String description;
}
