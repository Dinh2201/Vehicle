package com.example.vehicle.mappers;

import com.example.vehicle.dtos.response.DriverVehicleHistoryResponse;
import com.example.vehicle.entities.DriverVehicleHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DriverVehicleHistoryMapper {

    DriverVehicleHistoryResponse toResponse(DriverVehicleHistory driverVehicleHistory);
}
