package com.example.vehicle.mappers;

import com.example.vehicle.dtos.request.VehicleCreationRequest;
import com.example.vehicle.entities.vehicle.VehicleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleEntity vehicleToRequest(VehicleCreationRequest request);
}
