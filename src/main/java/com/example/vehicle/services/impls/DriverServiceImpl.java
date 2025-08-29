package com.example.vehicle.services.impls;

import com.example.vehicle.dtos.request.BookingRequest;
import com.example.vehicle.dtos.request.Driver.DriverCreationRequest;
import com.example.vehicle.dtos.response.Driver.DriverResponse;
import com.example.vehicle.entities.vehicle.Driver;
import com.example.vehicle.entities.vehicle.Vehicle;
import com.example.vehicle.mappers.DriverMapper;
import com.example.vehicle.repositories.DriverRepository;
import com.example.vehicle.services.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

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
        List<Driver> drivers = driverRepository.findAll();
        List<DriverResponse> responses = drivers.stream()
                .map(driverMapper::toResponse)
                .collect(Collectors.toList());
        return responses;
    }

    public Driver getDriverEntityById(long id) {
        return driverRepository.findById(id).orElseThrow(() -> new RuntimeException("Driver not found by id: " + id));
    }
    @Override
    public DriverResponse getDriverById(Long id) {
        log.info("VehicleType start get Driver by id ...");
        return driverMapper.toResponse(getDriverEntityById(id));
    }

    @Override
    public DriverResponse updateDriver(Long id, DriverCreationRequest request) {
        log.info("VehicleType start update driver ...");
        Driver driver = getDriverEntityById(id);
        driverMapper.updateDriver(driver, request);
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


    @Override
    public boolean acceptBooking(Long id, BookingRequest request) {
        // Trả về kết quả trực tiếp
        return request.getIsAccept();
    }
}
