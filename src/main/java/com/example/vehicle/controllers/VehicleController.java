package com.example.vehicle.controllers;

import com.example.vehicle.dtos.request.VehicleCreationRequest;
import com.example.vehicle.entities.vehicle.VehicleEntity;
import com.example.vehicle.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/v1")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/vehicles")
   public VehicleEntity createVehicle(@RequestBody VehicleCreationRequest request) {
        return vehicleService.createVehicle(request);
    }


}
