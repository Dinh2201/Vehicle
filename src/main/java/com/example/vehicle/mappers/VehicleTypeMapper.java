package com.example.vehicle.mappers;

import com.example.vehicle.dtos.request.VehicleType.VehicleTypeCreationRequest;
import com.example.vehicle.dtos.response.VehicleType.VehicleTypeResponse;
import com.example.vehicle.entities.vehicle.VehicleType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VehicleTypeMapper {


    VehicleType toRequest(VehicleTypeCreationRequest request);

    VehicleTypeResponse toCreationResponse(VehicleType vehicleType);

    void updateVehicleType(@MappingTarget VehicleType vehicleType, VehicleTypeCreationRequest request);
}
