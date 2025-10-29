package com.example.vehicle.services.impls;

import com.example.vehicle.configs.Translator;
import com.example.vehicle.dtos.request.driver.DriverRequest;
import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.driver.DriverResponse;
import com.example.vehicle.entities.BookingHistory;
import com.example.vehicle.entities.Driver;
import com.example.vehicle.entities.Vehicle;
import com.example.vehicle.enums.BookingAction;
import com.example.vehicle.enums.ErrorCode;
import com.example.vehicle.enums.SuccessCode;
import com.example.vehicle.exceptions.AppException;
import com.example.vehicle.mappers.DriverMapper;
import com.example.vehicle.messaging.DriverProducer;
import com.example.vehicle.repositories.BookingHistoryRepository;
import com.example.vehicle.repositories.DriverRepository;
import com.example.vehicle.repositories.VehicleRepository;
import com.example.vehicle.services.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    private final VehicleRepository vehicleRepository;
    private final BookingHistoryRepository bookingHistoryRepository;
    private final DriverProducer driverProducer;


    @Override
    public ApiResponse<DriverResponse> createDriver (DriverRequest request){
        log.info("VehicleType start create ...");
        ApiResponse<DriverResponse> apiResponse = new ApiResponse<>();

        Driver driver = driverMapper.toRequest(request);
        Driver response = driverRepository.save(driver);
        DriverResponse driverResponse = driverMapper.toResponse(response);

        apiResponse.setCode(SuccessCode.DRIVER_CREATE.getCode());
        apiResponse.setData(driverResponse);
        return apiResponse;
    }

    @Override
    public ApiResponse<List<DriverResponse>> getAllDrivers(int pageNo, int pageSize, String sortBy, String sortDir) {
        log.info("VehicleType start get All Drivers ...");

        log.info("Paging");
        Sort sort = sortDir.equalsIgnoreCase("ASC")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        List<Driver> drivers = driverRepository.findAll(pageable).getContent();

        List<DriverResponse> responses = driverMapper.toListResponse(drivers);

        ApiResponse<List<DriverResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.DRIVER_GET_ALL.getCode());
        apiResponse.setData(responses);
        log.info("VehicleType end get All Drivers ...");
        return apiResponse;
    }


    @Override
    public ApiResponse<DriverResponse> getDriverById(Long id) {
        log.info("VehicleType start get Driver by id ...");
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DRIVER_EXCEPTION));
        DriverResponse response = driverMapper.toResponse(driver);

        ApiResponse<DriverResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.DRIVER_GET_BY_ID.getCode());
        apiResponse.setData(response);
        return apiResponse;
    }

    @Override
    public ApiResponse<DriverResponse> updateDriver(Long id, DriverRequest request) {
        log.info("VehicleType start update driver ...");
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.DRIVER_EXCEPTION));
        driverMapper.updateDriver(driver, request);

        Driver newDriver = driverRepository.save(driver);
        DriverResponse response = driverMapper.toResponse(newDriver);

        ApiResponse<DriverResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.DRIVER_UPDATE.getCode());
        apiResponse.setData(response);
        return apiResponse;
    }

    @Override
    public ApiResponse<Boolean> deleteDriver(List<Long> ids) {
        log.info("VehicleType start delete driver ...");
        if(ids == null || ids.isEmpty()) {
            throw new AppException(ErrorCode.DRIVERS_NOT_FOUND);
        }

        List<Driver> drivers = driverRepository.findAllById(ids);

        if( drivers.size() != ids.size() ){
            throw new AppException(ErrorCode.DRIVERS_NOT_FOUND);
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

        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.DRIVER_DELETED.getCode());
        apiResponse.setMessage(Translator.toLocale(SuccessCode.DRIVER_DELETED.getCode()));
        apiResponse.setData(true);
        return apiResponse;
    }


    @Override
    public ApiResponse<Boolean> handelBookingAction(Long id, String action,  @Nullable Long bookingId, boolean isFromDriver) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();

        BookingAction bookingAction ;
        try {
            bookingAction = BookingAction.valueOf(action.toUpperCase()); // giúp khớp vs trong enum
        } catch (IllegalArgumentException e) {
            apiResponse.setData(false);
            return apiResponse;
        }

        Driver driver = driverRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.DRIVER_EXCEPTION));

        BookingHistory bookingHistory = new BookingHistory();
        bookingHistory.setDriver(driver);
        bookingHistory.setVehicle(driver.getVehicle());
        bookingHistory.setUpdatedAt(LocalDateTime.now());

        try{
            switch (bookingAction) {
                case ACCEPT :
                    bookingHistory.setBookingStatus("ACCEPTED");


                    apiResponse.setCode(SuccessCode.DRIVER_ACCEPT.getCode());
                    apiResponse.setMessage(Translator.toLocale(SuccessCode.DRIVER_ACCEPT.getCode()));
                    apiResponse.setData(true);
                    break;
                case REJECT:
                    bookingHistory.setBookingStatus("REJECTED");


                    apiResponse.setCode(SuccessCode.DRIVER_REJECT.getCode());
                    apiResponse.setMessage(Translator.toLocale(SuccessCode.DRIVER_REJECT.getCode()));
                    apiResponse.setData(false);
                    break;

                case CANCEL:
                    bookingHistory.setBookingId(bookingId);
                    bookingHistory.setBookingStatus(isFromDriver ? "CANCELLED BY DRIVER" : "CANCELLED BY USER");

                    String code = isFromDriver ? SuccessCode.DRIVER_CANCEL.getCode() : SuccessCode.USER_CANCEL.getCode();

                    apiResponse.setCode(code);
                    apiResponse.setMessage(Translator.toLocale(code));
                    apiResponse.setData(false);
                    break;

                default:
                    apiResponse.setData(false);
            }
            bookingHistoryRepository.save(bookingHistory);
            driverProducer.producerDriverAction(id, action, bookingId, isFromDriver);

        }catch (AppException e){
            apiResponse.setCode(ErrorCode.ERROR_SAVING_BOOKING_HISTORY.getCode());
            apiResponse.setMessage(Translator.toLocale(ErrorCode.ERROR_SAVING_BOOKING_HISTORY.getCode()));
            apiResponse.setData(false);
        }


        return apiResponse;
        //gọi produce message qua noti
    }
}
