package com.example.vehicle.controllers;

import com.example.vehicle.dtos.request.DispatchRequest;
import com.example.vehicle.dtos.request.Driver.DriverCreationRequest;
import com.example.vehicle.dtos.response.Driver.DriverResponse;
import com.example.vehicle.services.DriverService;
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
public class DriverController {
    DriverService driverService;

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

    @DeleteMapping("/driver/delete")
    public ResponseEntity<Boolean> deleteDriver(@RequestBody List<Long> ids){
        return ResponseEntity.ok(driverService.deleteDriver(ids));

    }

    // Endpoint để chấp nhận tài xế
    @PutMapping("/driver/{driverId}/accept")
    public ResponseEntity<Boolean> isAcceptBooking(@PathVariable Long driverId, @RequestBody DispatchRequest dispatchRequest) {
        return ResponseEntity.ok(driverService.acceptBooking(driverId, dispatchRequest));
    }

}
