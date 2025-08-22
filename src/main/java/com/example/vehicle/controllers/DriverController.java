package com.example.vehicle.controllers;

import com.example.vehicle.dtos.request.Driver.DriverCreationRequest;
import com.example.vehicle.dtos.response.Driver.DriverResponse;
import com.example.vehicle.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/v1")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @PostMapping("/drivers")
    public ResponseEntity<DriverResponse> createDriver (@RequestBody DriverCreationRequest request){
        return ResponseEntity.ok(driverService.createDriver(request));
    }
}
