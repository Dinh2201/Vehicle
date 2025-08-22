package com.example.vehicle.controllers;

import com.example.vehicle.dtos.request.VehicleType.VehicleTypeCreationRequest;
import com.example.vehicle.dtos.response.VehicleType.VehicleTypeResponse;
import com.example.vehicle.entities.vehicle.VehicleType;
import com.example.vehicle.services.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/v1")
public class VehicleTypeController {
    @Autowired
    private VehicleTypeService vehicleTypeService;

    @PostMapping("/vehicletypes")
    public ResponseEntity<VehicleTypeResponse>  createVehicleType(@RequestBody VehicleTypeCreationRequest request) {
        return ResponseEntity.ok(vehicleTypeService.create(request));
    }

    @GetMapping("/vehicletypes")
    public ResponseEntity<List<VehicleTypeResponse>> getAllVehicleTypes() {
        return ResponseEntity.ok(vehicleTypeService.getAllVehicleTypes());
    }

    @GetMapping("/vehicletype/{id}")
    public ResponseEntity<VehicleTypeResponse> getVehicleTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleTypeService.getVehicleTypeById(id));
    }

    @PutMapping("/vehicletype/update/{id}")
    public ResponseEntity<VehicleTypeResponse> updateVehicleType(@PathVariable Long id, @RequestBody VehicleTypeCreationRequest request){
        return ResponseEntity.ok(vehicleTypeService.updateVehicleType(id, request));
    }

    @PutMapping("/vehicletype/delete")
    public ResponseEntity<Boolean> deleteVehicleType(@RequestBody List<Long> id) {
        return ResponseEntity.ok(vehicleTypeService.deleteVehicleType(id));
    }
}
