package com.example.vehicle.controllers;

import com.example.vehicle.dtos.request.VehicleType.VehicleTypeCreationRequest;
import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.VehicleType.VehicleTypeResponse;
import com.example.vehicle.services.VehicleTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VehicleTypeController {
     VehicleTypeService vehicleTypeService;

    @PostMapping("/vehicletypes")
    public ResponseEntity<ApiResponse<VehicleTypeResponse>>  createVehicleType(@RequestBody VehicleTypeCreationRequest request) {
        ApiResponse<VehicleTypeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(vehicleTypeService.create(request));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/vehicletypes")
    public ResponseEntity<ApiResponse<List<VehicleTypeResponse>>> getAllVehicleTypes() {
        ApiResponse<List<VehicleTypeResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(vehicleTypeService.getAllVehicleTypes());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/vehicletype/{id}")
    public ResponseEntity<ApiResponse<VehicleTypeResponse>> getVehicleTypeById(@PathVariable Long id) {
        ApiResponse<VehicleTypeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(vehicleTypeService.getVehicleTypeById(id));
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/vehicletype/update/{id}")
    public ResponseEntity<ApiResponse<VehicleTypeResponse>> updateVehicleType(@PathVariable Long id, @RequestBody VehicleTypeCreationRequest request){
        ApiResponse<VehicleTypeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(vehicleTypeService.updateVehicleType(id, request));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/vehicletype/delete")
    public ResponseEntity<ApiResponse<Boolean>> deleteVehicleType(@RequestBody List<Long> id) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setResult(vehicleTypeService.deleteVehicleType(id));
        return ResponseEntity.ok(apiResponse);
    }
}
