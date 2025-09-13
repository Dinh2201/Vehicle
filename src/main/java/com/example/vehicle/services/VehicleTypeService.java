package com.example.vehicle.services;

import com.example.vehicle.dtos.request.VehicleType.VehicleTypeCreationRequest;
import com.example.vehicle.dtos.response.vehicletype.VehicleTypeResponse;

import java.util.List;

public interface VehicleTypeService {
    VehicleTypeResponse create(VehicleTypeCreationRequest request);

    List<VehicleTypeResponse> getAllVehicleTypes();

    VehicleTypeResponse getVehicleTypeById(Long id);

    VehicleTypeResponse updateVehicleType(Long id, VehicleTypeCreationRequest request);

    boolean deleteVehicleType(List<Long> ids);


}
