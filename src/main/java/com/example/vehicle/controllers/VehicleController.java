package com.example.vehicle.controllers;

import com.example.vehicle.dtos.request.Vehicle.VehicleCreationRequest;
import com.example.vehicle.dtos.request.Vehicle.VehicleUpdateRequest;
import com.example.vehicle.dtos.response.VehicleCreationResponse;
import com.example.vehicle.entities.vehicle.Vehicle;
import com.example.vehicle.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/v1")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/vehicles")
    public ResponseEntity<VehicleCreationResponse> createVehicle(@RequestBody VehicleCreationRequest request) {
        return ResponseEntity.ok(vehicleService.createVehicle(request));}

    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleCreationResponse> > getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
   }

    @GetMapping("/vehicle/{id}")
    public ResponseEntity<VehicleCreationResponse> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
   }

   @PutMapping("/vehicle/update/{id}")
    public ResponseEntity<VehicleCreationResponse> updateVehicle(@PathVariable Long id, @RequestBody VehicleUpdateRequest request) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, request));
   }

   @PutMapping("/vehicle/delete")
    public boolean deleteVehicle(@RequestBody List<Long> ids) {
        return vehicleService.deleteVehicle(ids);
   }

}
