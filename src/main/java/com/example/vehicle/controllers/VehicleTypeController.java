package com.example.vehicle.controllers;

import com.example.vehicle.dtos.request.VehicleType.VehicleTypeCreationRequest;
import com.example.vehicle.dtos.response.VehicleType.VehicleTypeResponse;
import com.example.vehicle.entities.vehicle.VehicleType;
import com.example.vehicle.services.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/v1")
public class VehicleTypeController {
    @Autowired
    private VehicleTypeService vehicleTypeService;

    @PostMapping("/vehicletypes")
    public ResponseEntity<VehicleTypeResponse>  createVehicleType(@RequestBody VehicleTypeCreationRequest request) {
        return ResponseEntity.ok(vehicleTypeService.create(request));
    }
}
