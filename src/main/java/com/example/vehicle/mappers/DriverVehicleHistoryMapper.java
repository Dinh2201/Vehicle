package com.example.vehicle.mappers;

import com.example.vehicle.dtos.response.DriverVehicleHistoryResponse;
import com.example.vehicle.entities.DriverVehicleHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DriverVehicleHistoryMapper {
    @Mapping(source = "vehicle.vehicleId", target = "vehicleId")
    @Mapping(source = "driver.driverId", target = "driverId")
    DriverVehicleHistoryResponse toResponse(DriverVehicleHistory driverVehicleHistory);
}
