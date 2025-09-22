package com.example.vehicle.controllers;

import com.example.vehicle.dtos.request.driver.DriverRequest;
import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.driver.DriverResponse;
import com.example.vehicle.entities.DriverNotification;
import com.example.vehicle.repositories.DriverNotificationRepository;
import com.example.vehicle.repositories.DriverRepository;
import com.example.vehicle.services.DriverNotificationService;
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
    DriverNotificationRepository driverNotificationRepository;
    DriverNotificationService driverNotificationService;
    private final DriverRepository driverRepository;


    @PostMapping("/driver/create")
    public ResponseEntity<ApiResponse<DriverResponse>> createDriver (@RequestBody DriverRequest request){
        ApiResponse<DriverResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(driverService.createDriver(request));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/drivers")
    public ResponseEntity<ApiResponse<List<DriverResponse>>> getAllDrivers(){
        ApiResponse<List<DriverResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(driverService.getAllDrivers());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<ApiResponse<DriverResponse>> getDriverById(@PathVariable Long id){
        ApiResponse<DriverResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(driverService.getDriverById(id));
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/driver/update/{id}")
    public ResponseEntity<ApiResponse<DriverResponse>> updateDriver(@PathVariable Long id, @RequestBody DriverRequest request){
        ApiResponse<DriverResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(driverService.updateDriver(id, request));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/driver/delete")
    public ResponseEntity<ApiResponse<Boolean>> deleteDriver(@RequestBody List<Long> ids){
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setResult(driverService.deleteDriver(ids));
        return ResponseEntity.ok(apiResponse);

    }

    // Endpoint để chấp nhận tài xế
    @PutMapping("/driver/{driverId}/accept")
    public ResponseEntity<ApiResponse<Boolean>> isAcceptBooking(@PathVariable Long driverId, @RequestParam("action") String action) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setResult(driverService.acceptBooking(driverId, action));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/driver/{id}/notifications")
    public ResponseEntity<List<DriverNotification>> getDriverNotifications(@PathVariable Long id) {
        if (!driverRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        List<DriverNotification> notifications = driverNotificationService.getDriverNotifications(id);
        return ResponseEntity.ok(notifications);
    }

}
