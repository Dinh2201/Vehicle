package com.example.vehicle.controllers;

import com.example.vehicle.dtos.request.Vehicle.VehicleCreationRequest;
import com.example.vehicle.dtos.request.Vehicle.VehicleUpdateRequest;
import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.Vehicle.VehicleResponse;
import com.example.vehicle.services.VehicleService;
import jakarta.validation.Valid;
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
public class VehicleController {

     VehicleService vehicleService;

    @PostMapping("/vehicles")
    public ResponseEntity<ApiResponse<VehicleResponse>> createVehicle(@RequestBody @Valid VehicleCreationRequest request) {
            ApiResponse<VehicleResponse> response = new ApiResponse<>();
            response.setResult(vehicleService.createVehicle(request));
            return ResponseEntity.ok(response);
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleResponse> > getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
   }

    @GetMapping("/vehicle/{id}")
    public ResponseEntity<ApiResponse<VehicleResponse>> getVehicleById(@PathVariable Long id) {
        ApiResponse<VehicleResponse> response = new ApiResponse<>();
        response.setResult(vehicleService.getVehicleById(id));
        return ResponseEntity.ok(response);
   }

   @PutMapping("/vehicle/update/{id}")
    public ResponseEntity<ApiResponse<VehicleResponse>> updateVehicle(@PathVariable Long id, @RequestBody VehicleUpdateRequest request) {
        ApiResponse<VehicleResponse> response = new ApiResponse<>();
        response.setResult(vehicleService.updateVehicle(id, request));
        return ResponseEntity.ok(response);
   }

   @PutMapping("/vehicle/{id}/location")
   public ResponseEntity<VehicleResponse> updateVehicleLocation(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.updateVehicleLocation(id));
   }

   @DeleteMapping("/vehicle/delete")
    public boolean deleteVehicle(@RequestBody List<Long> ids) {
        return vehicleService.deleteVehicle(ids);
   }

}
