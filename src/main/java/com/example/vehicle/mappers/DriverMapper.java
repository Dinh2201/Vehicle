package com.example.vehicle.mappers;

import com.example.vehicle.dtos.request.Driver.DriverCreationRequest;
import com.example.vehicle.dtos.response.Driver.DriverResponse;
import com.example.vehicle.entities.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    Driver toRequest(DriverCreationRequest driver);

    DriverResponse toResponse(Driver driver);

    void updateDriver(@MappingTarget Driver driver, DriverCreationRequest driverCreationRequest);
}
