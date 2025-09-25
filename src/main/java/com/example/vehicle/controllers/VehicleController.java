package com.example.vehicle.controllers;

import com.example.vehicle.dtos.request.vehicle.VehicleCreationRequest;
import com.example.vehicle.dtos.request.vehicle.VehicleUpdateRequest;
import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.vehicle.VehicleResponse;
import com.example.vehicle.services.VehicleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VehicleController {

     VehicleService vehicleService;

    @PostMapping("/vehicle/create")
    public ResponseEntity<ApiResponse<VehicleResponse>> createVehicle(@RequestBody @Valid VehicleCreationRequest request) {
            ApiResponse<VehicleResponse> apiResponse = new ApiResponse<>();
            apiResponse.setResult(vehicleService.createVehicle(request));
            return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/vehicles")
    public ResponseEntity<ApiResponse<List<VehicleResponse>> > getAllVehicles(
            @RequestParam(required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false, defaultValue = "5") int pageSize,
            @RequestParam(required = false, defaultValue = "vehicleId") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") String sortDir
    ) {
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("ASC")){
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }
        ApiResponse<List<VehicleResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(vehicleService.getAllVehicles(PageRequest.of(pageNo-1, pageSize, sort)));
        return ResponseEntity.ok(apiResponse);
   }

    @GetMapping("/vehicle/{id}")
    public ResponseEntity<ApiResponse<VehicleResponse>> getVehicleById(@PathVariable Long id) {
        ApiResponse<VehicleResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(vehicleService.getVehicleById(id));
        return ResponseEntity.ok(apiResponse);
   }

   @PutMapping("/vehicle/update/{id}")
    public ResponseEntity<ApiResponse<VehicleResponse>> updateVehicle(@PathVariable Long id, @RequestBody VehicleUpdateRequest request) {
        ApiResponse<VehicleResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(vehicleService.updateVehicle(id, request));
        return ResponseEntity.ok(apiResponse);
   }

   @DeleteMapping("/vehicle/delete")
    public ResponseEntity<ApiResponse<Boolean>> deleteVehicle(@RequestBody List<Long> ids) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setResult(vehicleService.deleteVehicle(ids));
        return ResponseEntity.ok(apiResponse);
   }

}
