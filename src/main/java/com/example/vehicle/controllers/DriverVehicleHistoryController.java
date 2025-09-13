package com.example.vehicle.controllers;

import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.DriverVehicleHistoryResponse;
import com.example.vehicle.services.DriverVehicleHistoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DriverVehicleHistoryController {

    DriverVehicleHistoryService driverVehicleHistoryService;

    @GetMapping("/history")
    public ResponseEntity<ApiResponse<List<DriverVehicleHistoryResponse>>> getDriverVehicleHistory() {
        ApiResponse<List<DriverVehicleHistoryResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(driverVehicleHistoryService.getAllHistory());
        return ResponseEntity.ok(apiResponse);
    }
}
