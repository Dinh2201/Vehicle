package com.example.vehicle.services;

import com.example.vehicle.dtos.request.vehicletype.VehicleTypeRequest;
import com.example.vehicle.dtos.response.vehicletype.VehicleTypeResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehicleTypeService {
    VehicleTypeResponse create(VehicleTypeRequest request);

    List<VehicleTypeResponse> getAllVehicleTypes(Pageable pageable);

    VehicleTypeResponse getVehicleTypeById(Long id);

    VehicleTypeResponse updateVehicleType(Long id, VehicleTypeRequest request);

    boolean deleteVehicleType(List<Long> ids);


}
