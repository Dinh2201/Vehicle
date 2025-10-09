package com.example.vehicle.controllers;

import com.example.vehicle.dtos.request.driver.DriverRequest;
import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.driver.DriverResponse;
import com.example.vehicle.services.DriverService;
import jakarta.validation.Valid;
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


    @PostMapping("/driver/create")
    public ResponseEntity<ApiResponse<DriverResponse>> createDriver (@RequestBody @Valid DriverRequest request){
        return ResponseEntity.ok(driverService.createDriver(request));
    }

    @GetMapping("/drivers")
    public ResponseEntity<ApiResponse<List<DriverResponse>>> getAllDrivers
            (@RequestParam(required = false, defaultValue = "1") int pageNo,
                  @RequestParam(required = false, defaultValue = "5") int pageSize,
                  @RequestParam(required = false, defaultValue = "driverId") String sortBy,
                  @RequestParam(required = false, defaultValue = "ASC") String sortDir){ //truyền vô service luôn


        return ResponseEntity.ok(driverService.getAllDrivers(pageNo , pageSize, sortBy,sortDir ));
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<ApiResponse<DriverResponse>> getDriverById(@PathVariable Long id){
        return ResponseEntity.ok(driverService.getDriverById(id));
    }

    @PutMapping("/driver/update/{id}")
    public ResponseEntity<ApiResponse<DriverResponse>> updateDriver(@PathVariable Long id, @RequestBody DriverRequest request){
        return ResponseEntity.ok(driverService.updateDriver(id, request));
    }

    @DeleteMapping("/driver/delete")
    public ResponseEntity<ApiResponse<Boolean>> deleteDriver(@RequestBody List<Long> ids){
        return ResponseEntity.ok(driverService.deleteDriver(ids));

    }

    // Endpoint để chấp nhận tài xế
    @PutMapping("/driver/{driverId}")
    public ResponseEntity<ApiResponse<Boolean>> isAcceptBooking(@PathVariable Long driverId, @RequestParam("action") String action) {
        return ResponseEntity.ok(driverService.acceptBooking(driverId, action,null));
    }


}
