package com.example.vehicle.controllers;

import com.example.vehicle.dtos.request.Driver.DriverCreationRequest;
import com.example.vehicle.dtos.response.Driver.DriverResponse;
import com.example.vehicle.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/v1")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @PostMapping("/drivers")
    public ResponseEntity<DriverResponse> createDriver (@RequestBody DriverCreationRequest request){
        return ResponseEntity.ok(driverService.createDriver(request));
    }

    @GetMapping("/drivers")
    public ResponseEntity<List<DriverResponse>> getAllDrivers(){
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<DriverResponse> getDriverById(@PathVariable Long id){
        return ResponseEntity.ok(driverService.getDriverById(id));
    }

    @PutMapping("/driver/update/{id}")
    public ResponseEntity<DriverResponse> updateDriver(@PathVariable Long id, @RequestBody DriverCreationRequest request){
        return ResponseEntity.ok(driverService.updateDriver(id, request));
    }

    @PutMapping("/driver/delete")
    public ResponseEntity<Boolean> deleteDriver(@RequestBody List<Long> ids){
        return ResponseEntity.ok(driverService.deleteDriver(ids));

    }
}
