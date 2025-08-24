package com.example.vehicle.mappers;

import com.example.vehicle.dtos.request.VehicleType.VehicleTypeCreationRequest;
import com.example.vehicle.dtos.response.Vehicle.VehicleResponse;
import com.example.vehicle.dtos.response.VehicleType.VehicleTypeResponse;
import com.example.vehicle.entities.vehicle.Vehicle;
import com.example.vehicle.entities.vehicle.VehicleType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VehicleTypeMapper {


    VehicleType toRequest(VehicleTypeCreationRequest request);

    @Mapping(source = "vehicles", target = "vehicles")
    VehicleTypeResponse toResponse(VehicleType vehicleType);

    // Map Vehicle sang VehicleResponse
    VehicleResponse toVehicleResponse(Vehicle vehicle);

    void updateVehicleType(@MappingTarget VehicleType vehicleType, VehicleTypeCreationRequest request);
}
