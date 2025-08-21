package com.example.vehicle.mappers;

import com.example.vehicle.dtos.request.Driver.DriverCreationRequest;
import com.example.vehicle.entities.vehicle.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    Driver toDriverEntity(DriverCreationRequest driver);
}
