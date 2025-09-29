package com.example.vehicle.services;

import com.example.vehicle.dtos.request.vehicle.VehicleCreationRequest;
import com.example.vehicle.dtos.request.vehicle.VehicleUpdateRequest;
import com.example.vehicle.dtos.response.vehicle.VehicleResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehicleService {
    VehicleResponse createVehicle(VehicleCreationRequest request);

    List<VehicleResponse> getAllVehicles(Pageable pageable);

    VehicleResponse getVehicleById(Long id);

    VehicleResponse updateVehicle(Long id, VehicleUpdateRequest request);

    boolean deleteVehicle(List<Long> ids);
}
