package com.example.vehicle.mappers;

import com.example.vehicle.dtos.request.driver.DriverRequest;
import com.example.vehicle.dtos.response.driver.DriverResponse;
import com.example.vehicle.entities.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    Driver toRequest(DriverRequest driver);

    DriverResponse toResponse(Driver driver);

    List<DriverResponse> toListResponse(List<Driver> driver);


    void updateDriver(@MappingTarget Driver driver, DriverRequest request);
}
