package com.example.vehicle.mappers;

import com.example.vehicle.dtos.request.VehicleType.VehicleTypeCreationRequest;
import com.example.vehicle.entities.vehicle.VehicleType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleTypeMapper {
    VehicleType toVehicleType(VehicleTypeCreationRequest request);
}
