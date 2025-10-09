package com.example.vehicle.controllers;

import com.example.vehicle.dtos.request.vehicletype.VehicleTypeRequest;
import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.vehicletype.VehicleTypeResponse;
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

    @PostMapping("/vehicletype/create")
    public ResponseEntity<ApiResponse<VehicleTypeResponse>>  createVehicleType(@RequestBody VehicleTypeRequest request) {
        return ResponseEntity.ok(vehicleTypeService.create(request));
    }

    @GetMapping("/vehicletypes")
    public ResponseEntity<ApiResponse<List<VehicleTypeResponse>>> getAllVehicleTypes(
            @RequestParam(required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false, defaultValue = "5") int pageSize,
            @RequestParam(required = false, defaultValue = "vehicleTypeId") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") String sortDir
    ) {

        return ResponseEntity.ok(vehicleTypeService.getAllVehicleTypes(pageNo , pageSize, sortBy,sortDir));
    }

    @GetMapping("/vehicletype/{id}")
    public ResponseEntity<ApiResponse<VehicleTypeResponse>> getVehicleTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleTypeService.getVehicleTypeById(id));
    }

    @PutMapping("/vehicletype/update/{id}")
    public ResponseEntity<ApiResponse<VehicleTypeResponse>> updateVehicleType(@PathVariable Long id, @RequestBody VehicleTypeRequest request){
        return ResponseEntity.ok(vehicleTypeService.updateVehicleType(id, request));
    }

    @DeleteMapping("/vehicletype/delete")
    public ResponseEntity<ApiResponse<Boolean>> deleteVehicleType(@RequestBody List<Long> id) {
        return ResponseEntity.ok(vehicleTypeService.deleteVehicleType(id));
    }
}
