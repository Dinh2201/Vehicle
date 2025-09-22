package com.example.vehicle.services.impls;

import com.example.vehicle.dtos.request.driver.DriverRequest;
import com.example.vehicle.dtos.response.driver.DriverResponse;
import com.example.vehicle.entities.BookingHistory;
import com.example.vehicle.entities.Driver;
import com.example.vehicle.entities.Vehicle;
import com.example.vehicle.enums.BookingAction;
import com.example.vehicle.exceptions.AppException;
import com.example.vehicle.exceptions.ErrorCode;
import com.example.vehicle.mappers.DriverMapper;
import com.example.vehicle.repositories.BookingHistoryRepository;
import com.example.vehicle.repositories.DriverRepository;
import com.example.vehicle.repositories.VehicleRepository;
import com.example.vehicle.services.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    private final VehicleRepository vehicleRepository;
    private final BookingHistoryRepository bookingHistoryRepository;


    @Override
    public DriverResponse createDriver (DriverRequest request){
        log.info("VehicleType start create ...");

        Driver driver = driverMapper.toRequest(request);
        Driver response = driverRepository.save(driver);
        return driverMapper.toResponse(response);
    }

    @Override
    public List<DriverResponse> getAllDrivers() {
        log.info("VehicleType start get All Drivers ...");
        List<Driver> drivers = driverRepository.findAllByOrderByDriverIdAsc();

        List<DriverResponse> responses = driverMapper.toListResponse(drivers);
        log.info("VehicleType end get All Drivers ...");
        return responses;
    }


    @Override
    public DriverResponse getDriverById(Long id) {
        log.info("VehicleType start get Driver by id ...");
        return driverMapper.toResponse(driverRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.DRIVER_EXCEPTION)));
    }

    @Override
    public DriverResponse updateDriver(Long id, DriverRequest request) {
        log.info("VehicleType start update driver ...");
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.DRIVER_EXCEPTION));
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
            Vehicle vehicle = driver.getVehicle();
            if (vehicle != null) {
                vehicle.setDriver(null);
                vehicleRepository.save(vehicle);
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

        Driver driver = driverRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.DRIVER_EXCEPTION));

        BookingHistory bookingHistory = new BookingHistory();
        bookingHistory.setDriver(driver);
        bookingHistory.setVehicle(driver.getVehicle());
        bookingHistory.setUpdatedAt(LocalDateTime.now());

        switch (bookingAction) {
            case ACCEPT :
                bookingHistory.setBookingStatus("ACCEPTED");

                bookingHistoryRepository.save(bookingHistory);
                return true;
            case REJECT:
                bookingHistory.setBookingStatus("REJECTED");

                bookingHistoryRepository.save(bookingHistory);
                return false;

            case CANCEL:
                bookingHistory.setBookingStatus("CANCELLED");
                bookingHistoryRepository.save(bookingHistory);
                return false;

            default:
                return false;
        }
    }
}
