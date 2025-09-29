package com.example.vehicle.controllers;

import com.example.vehicle.configs.Translator;
import com.example.vehicle.dtos.request.driver.DriverRequest;
import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.DriverNotificationResponse;
import com.example.vehicle.dtos.response.driver.DriverResponse;
import com.example.vehicle.enums.SuccessCode;
import com.example.vehicle.repositories.DriverNotificationRepository;
import com.example.vehicle.repositories.DriverRepository;
import com.example.vehicle.services.DriverNotificationService;
import com.example.vehicle.services.DriverService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<ApiResponse<DriverResponse>> createDriver (@RequestBody @Valid DriverRequest request){
        ApiResponse<DriverResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.DRIVER_CREATE.getCode());
        apiResponse.setResult(driverService.createDriver(request));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/drivers")
    public ResponseEntity<ApiResponse<List<DriverResponse>>> getAllDrivers
            (@RequestParam(required = false, defaultValue = "1") int pageNo,
                  @RequestParam(required = false, defaultValue = "5") int pageSize,
                  @RequestParam(required = false, defaultValue = "driverId") String sortBy,
                  @RequestParam(required = false, defaultValue = "ASC") String sortDir){
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("ASC")){
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        ApiResponse<List<DriverResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.DRIVER_GET_ALL.getCode());
        apiResponse.setResult(driverService.getAllDrivers(PageRequest.of(pageNo-1,pageSize, sort)));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<ApiResponse<DriverResponse>> getDriverById(@PathVariable Long id){
        ApiResponse<DriverResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.DRIVER_GET_BY_ID.getCode());
        apiResponse.setResult(driverService.getDriverById(id));
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/driver/update/{id}")
    public ResponseEntity<ApiResponse<DriverResponse>> updateDriver(@PathVariable Long id, @RequestBody DriverRequest request){
        ApiResponse<DriverResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.DRIVER_UPDATE.getCode());
        apiResponse.setResult(driverService.updateDriver(id, request));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/driver/delete")
    public ResponseEntity<ApiResponse<Boolean>> deleteDriver(@RequestBody List<Long> ids){
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.DRIVER_DELETED.getCode());
        apiResponse.setMessage(Translator.toLocale(SuccessCode.DRIVER_DELETED.getCode()));
        apiResponse.setResult(driverService.deleteDriver(ids));
        return ResponseEntity.ok(apiResponse);

    }

    // Endpoint để chấp nhận tài xế
    @PutMapping("/driver/{driverId}/accept")
    public ResponseEntity<ApiResponse<Boolean>> isAcceptBooking(@PathVariable Long driverId, @RequestParam("action") String action) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        boolean result = driverService.acceptBooking(driverId, action);

        if (result && action.equalsIgnoreCase("ACCEPT")) {
            apiResponse.setCode(SuccessCode.DRIVER_ACCEPT.getCode());
            apiResponse.setMessage(Translator.toLocale(SuccessCode.DRIVER_ACCEPT.getCode()));
        } else if (!result && action.equalsIgnoreCase("REJECT")) {
            apiResponse.setCode(SuccessCode.DRIVER_REJECT.getCode());
            apiResponse.setMessage(Translator.toLocale(SuccessCode.DRIVER_REJECT.getCode()));
        } else if (!result && action.equalsIgnoreCase("CANCEL")) {
            apiResponse.setCode(SuccessCode.DRIVER_CANCEL.getCode());
            apiResponse.setMessage(Translator.toLocale(SuccessCode.DRIVER_CANCEL.getCode()));
        } else {
            apiResponse.setMessage("Invalid booking action");
        }
        apiResponse.setResult(driverService.acceptBooking(driverId, action));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/driver/{id}/notifications")
    public ResponseEntity<ApiResponse<List<DriverNotificationResponse>>> getDriverNotifications(@PathVariable Long id) {
        ApiResponse<List<DriverNotificationResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.DRIVER_NOTIFY.getCode());
        if (!driverRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        List<DriverNotificationResponse> notifications = driverNotificationService.getDriverNotifications(id);
        apiResponse.setResult(notifications);
        return ResponseEntity.ok(apiResponse);
    }

}
