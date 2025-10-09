package com.example.vehicle.services;

import com.example.vehicle.dtos.request.vehicletype.VehicleTypeRequest;
import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.vehicletype.VehicleTypeResponse;

import java.util.List;

public interface VehicleTypeService {
    ApiResponse<VehicleTypeResponse> create(VehicleTypeRequest request);

    ApiResponse<List<VehicleTypeResponse>> getAllVehicleTypes(int pageNo, int pageSize, String sortBy, String sortDir);

    ApiResponse<VehicleTypeResponse> getVehicleTypeById(Long id);

    ApiResponse<VehicleTypeResponse> updateVehicleType(Long id, VehicleTypeRequest request);

    ApiResponse<Boolean> deleteVehicleType(List<Long> ids);


}
