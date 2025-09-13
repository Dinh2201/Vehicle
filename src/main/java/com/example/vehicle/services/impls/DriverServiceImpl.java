package com.example.vehicle.services.impls;

import com.example.vehicle.dtos.request.Driver.DriverCreationRequest;
import com.example.vehicle.dtos.response.Driver.DriverResponse;
import com.example.vehicle.entities.Driver;
import com.example.vehicle.entities.DriverVehicleHistory;
import com.example.vehicle.entities.Vehicle;
import com.example.vehicle.enums.BookingAction;
import com.example.vehicle.exceptions.AppException;
import com.example.vehicle.exceptions.ErrorCode;
import com.example.vehicle.mappers.DriverMapper;
import com.example.vehicle.repositories.DriverRepository;
import com.example.vehicle.repositories.DriverVehicleHistoryRepository;
import com.example.vehicle.repositories.VehicleRepository;
import com.example.vehicle.services.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    private final VehicleRepository vehicleRepository;
    private final DriverVehicleHistoryRepository historyRepository;


    @Override
    public DriverResponse createDriver (DriverCreationRequest request){
        log.info("VehicleType start create ...");

        Driver driver = driverMapper.toRequest(request);
        Driver response = driverRepository.save(driver);
        return driverMapper.toResponse(response);
    }

    @Override
    public List<DriverResponse> getAllDrivers() {
        log.info("VehicleType start get All Drivers ...");
        List<Driver> drivers = driverRepository.findAllByOrderByDriverIdAsc();
        List<DriverResponse> responses = drivers.stream()
                .map(driverMapper::toResponse)
                .collect(Collectors.toList());
        return responses;
    }


    @Override
    public DriverResponse getDriverById(Long id) {
        log.info("VehicleType start get Driver by id ...");
        return driverMapper.toResponse(driverRepository.findById(id).orElseThrow(() -> new RuntimeException("Driver not found by id: " + id)));
    }

    @Override
    public DriverResponse updateDriver(Long id, DriverCreationRequest request) {
        log.info("VehicleType start update driver ...");
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new RuntimeException("Driver not found by id: " + id));
        driverMapper.updateDriver(driver, request);

        if (request.getStatus() != null) {

            Set<Vehicle> vehicles = driver.getVehicles();

            for (Vehicle vehicle : vehicles) {  //là cú pháp của vòng lặp for-each (enhanced for loop) trong Java.
                vehicle.setStatus(request.getStatus()); // Đồng bộ status
                vehicleRepository.save(vehicle); // Lưu vehicle
            }
        }
        Driver newDriver = driverRepository.save(driver);
        return driverMapper.toResponse(newDriver);
    }

    @Override
    public boolean deleteDriver(List<Long> ids) {
        log.info("VehicleType start delete driver ...");
        if(ids == null || ids.isEmpty()) {
            return false;
        }

        List<Driver> drivers = driverRepository.findAllById(ids);

        if( drivers.size() != ids.size() ){
            throw new RuntimeException("Some drivers do not exist");
        }

        // Gỡ liên kết driver khỏi tất cả vehicle tương ứng
        for (Driver driver : drivers) {
            Set<Vehicle> vehicles = driver.getVehicles();
            for (Vehicle vehicle : vehicles) {
                vehicle.getDrivers().remove(driver);
            }
        }


        driverRepository.deleteAll(drivers);
        return true;
    }


//    @Override
//    public boolean acceptBooking(Long id, DispatchRequest request) {
//        return request.getIsAccept();
//    }
//    @Override
//    public boolean acceptBooking(Long id, String action) {
//        if ("accept".equalsIgnoreCase(action)) {
//            return true;
//        } else if ("reject".equalsIgnoreCase(action)) {
//            return false;
//    } //làm enum cái accept và reject  đổi thanh switch case
//        // Nếu không phải accept/reject thì mặc định reject
//        // thêm status cancle
//        return false;
//
//    }

    @Override
    public boolean acceptBooking(Long id, String action) {
        BookingAction bookingAction;
        try {
            bookingAction = BookingAction.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }

        Driver driver = driverRepository.findById(id).orElseThrow(() -> new RuntimeException("Driver not found by id: " + id));

        DriverVehicleHistory history = historyRepository.findByDriver(driver )
                .orElseThrow(() -> new AppException(ErrorCode.HISTORY_NOT_FOUND));

        switch (bookingAction) {
            case ACCEPT :
                history.setBookingStatus("ACCEPTED");
                history.setUpdatedAt(LocalDateTime.now());
                historyRepository.save(history);
                return true;
            case REJECT:
                history.setBookingStatus("REJECTED");
                history.setUpdatedAt(LocalDateTime.now());
                historyRepository.save(history);
                return false;

            default:
                return false;
        }
    }
}
