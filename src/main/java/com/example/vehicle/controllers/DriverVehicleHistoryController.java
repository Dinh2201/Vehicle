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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DriverVehicleHistoryController {

    DriverVehicleHistoryService driverVehicleHistoryService;

    @GetMapping("/history")
    public ResponseEntity<ApiResponse<List<DriverVehicleHistoryResponse>>> getDriverVehicleHistory(
            @RequestParam(required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false, defaultValue = "5") int pageSize,
            @RequestParam(required = false, defaultValue = "historyId") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") String sortDir
    ) {
        return ResponseEntity.ok(driverVehicleHistoryService.getAllHistory(pageNo , pageSize, sortBy,sortDir));
    }
}
