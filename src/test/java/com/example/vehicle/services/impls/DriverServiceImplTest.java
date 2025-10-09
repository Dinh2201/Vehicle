package com.example.vehicle.services.impls;

import com.example.vehicle.configs.Translator;
import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.driver.DriverResponse;
import com.example.vehicle.entities.BookingHistory;
import com.example.vehicle.entities.Driver;
import com.example.vehicle.entities.Vehicle;
import com.example.vehicle.enums.DriverStatus;
import com.example.vehicle.enums.ErrorCode;
import com.example.vehicle.exceptions.AppException;
import com.example.vehicle.mappers.DriverMapper;
import com.example.vehicle.messaging.DriverProducer;
import com.example.vehicle.repositories.BookingHistoryRepository;
import com.example.vehicle.repositories.DriverRepository;
import com.example.vehicle.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class DriverServiceImplTest {

    @InjectMocks   DriverServiceImpl driverServiceImpl;

   @Mock DriverRepository driverRepository;

    @Mock DriverMapper driverMapper;

    @Mock VehicleRepository vehicleRepository;

    @Mock BookingHistoryRepository bookingHistoryRepository;

    @Mock  DriverProducer driverProducer;

//    @Test
//    void getAccount_shouldReturnAccounts() {
//        // prepare data
//        var driverEntity = buildDriverEntities();
//        var driverResponses = buildDrivers();
//        Pageable pageable = PageRequest.of(0, 10);
//
//        // giả lập Page<Driver>
//        Page<Driver> driverPage = new PageImpl<>(driverEntity, pageable, driverEntity.size());
//
//        // Giả lập hành vi của mock
//        when(driverRepository.findAll(pageable)).thenReturn(driverPage);
//        when(driverMapper.toListResponse(driverEntity)).thenReturn(driverResponses);
//
//        // Gọi hàm và kiểm tra kết quả
//        var result = driverServiceImpl.getAllDrivers(pageable);
//
//        assertNotNull(result);
//        assertNotNull(result.getData());
//        assertEquals(driverResponses.size(), result.getData().size());
//        assertEquals(driverResponses.get(0).getDriverId(), result.getData().get(0).getDriverId());
//
//        // Đảm bảo method mock được gọi đúng
//        verify(driverRepository, times(1)).findAll(pageable);
//        verify(driverMapper, times(1)).toListResponse(driverEntity);
//    }

    @Test
    void getAllDrivers_shouldReturnDriverResponses() {
        // given: mock data
        List<Driver> driverEntities = buildDriverEntities(); // giả lập list entity
        List<DriverResponse> driverResponses = buildDrivers(); // giả lập list response

        int pageNo = 2;
        int pageSize = 10;
        String sortBy = "driverId";
        String sortDir = "ASC";

        Sort sort = Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        Page<Driver> driverPage = new PageImpl<>(driverEntities, pageable, driverEntities.size());

        // when: setup mock behavior
        when(driverRepository.findAll(pageable)).thenReturn(driverPage);
        when(driverMapper.toListResponse(driverEntities)).thenReturn(driverResponses);

        // then: call the method
        ApiResponse<List<DriverResponse>> result = driverServiceImpl.getAllDrivers(pageNo, pageSize, sortBy, sortDir);

        // assertions
        assertNotNull(result);
        assertNotNull(result.getData());
        assertEquals(driverResponses.size(), result.getData().size());
        assertEquals(driverResponses.get(0).getDriverId(), result.getData().get(0).getDriverId());

        // verify mocks
        verify(driverRepository, times(1)).findAll(pageable);
        verify(driverMapper, times(1)).toListResponse(driverEntities);
    }


    @BeforeEach
    void setupTranslator() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages"); // basename của messages.properties trong `src/main/resources`
        messageSource.setDefaultEncoding("UTF-8");

        Translator.messageSource = messageSource;
    }

    @Test
    void acceptBooking_whenSavingBookingHistoryFails_shouldHandleException() {
        Long driverId = 1L;
        String action = "ACCEPT";
        Driver mockDriver = buildDriver();

        when(driverRepository.findById(driverId)).thenReturn(Optional.of(mockDriver));
        when(bookingHistoryRepository.save(any(BookingHistory.class)))
                .thenThrow(new AppException(ErrorCode.ERROR_SAVING_BOOKING_HISTORY));

        ApiResponse<Boolean> result = driverServiceImpl.acceptBooking(driverId, action, null);

        assertFalse(result.getData());
        assertEquals(ErrorCode.ERROR_SAVING_BOOKING_HISTORY.getCode(), result.getCode());
        verify(driverProducer, never()).producerDriverAction(any(), any(), any());
    }

    @Test
    void acceptBooking_Reject_whenSavingBookingHistoryFails_shouldHandleException() {
        Long driverId = 1L;
        String action = "REJECT";
        Driver mockDriver = buildDriver();

        when(driverRepository.findById(driverId)).thenReturn(Optional.of(mockDriver));
        when(bookingHistoryRepository.save(any(BookingHistory.class)))
                .thenThrow(new AppException(ErrorCode.ERROR_SAVING_BOOKING_HISTORY));

        ApiResponse<Boolean> result = driverServiceImpl.acceptBooking(driverId, action, null);

        assertFalse(result.getData());
        assertEquals(ErrorCode.ERROR_SAVING_BOOKING_HISTORY.getCode(), result.getCode());
        verify(driverProducer, never()).producerDriverAction(any(), any(), any());
    }

    private Driver buildDriver() {
        Driver driver = new Driver();
        driver.setDriverId(1L);

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(100L);
        driver.setVehicle(vehicle);

        // Nếu bạn có thêm các field khác (email, name, license...), có thể thêm vào đây nếu cần
        return driver;
    }


    private List<DriverResponse> buildDrivers() {
        var driverResp1 = new DriverResponse();
        driverResp1.setDriverId(1L);
        driverResp1.setName("example1");
        driverResp1.setAge("20");
        driverResp1.setPhoneNumber("0123456789");
        driverResp1.setAddress("Ho Chi Minh");
        driverResp1.setIdentityCard("246813579");
        driverResp1.setSex("Male");
        driverResp1.setDriverLicense("A246813579");
        driverResp1.setStatus("ACTIVE");
        driverResp1.setAvgRating(new BigDecimal("4.5"));

        var driverResp2 = new DriverResponse();
        driverResp2.setDriverId(2L);
        driverResp2.setName("example2");
        driverResp2.setAge("30");
        driverResp2.setPhoneNumber("9876543210");
        driverResp2.setAddress("Hanoi");
        driverResp2.setIdentityCard("987654321");
        driverResp2.setSex("Female");
        driverResp1.setDriverLicense("A145813579");
        driverResp2.setStatus("INACTIVE");
        driverResp2.setAvgRating(new BigDecimal("4.0"));

        return List.of(driverResp1, driverResp2);
    }

    private List<Driver> buildDriverEntities() {
        var drivers = new ArrayList<Driver>();
        var driverResp1 = new Driver();
        driverResp1.setDriverId(1L);
        driverResp1.setName("example1");
        driverResp1.setAge("20");
        driverResp1.setPhoneNumber("0123456789");
        driverResp1.setAddress("Ho Chi Minh");
        driverResp1.setIdentityCard("246813579");
        driverResp1.setSex("Male");
        driverResp1.setStatus(DriverStatus.ACTIVE);
        driverResp1.setAvgRating(new BigDecimal("4.5"));

        var driverResp2 = new Driver();
        driverResp2.setDriverId(2L);
        driverResp2.setName("example1");
        driverResp2.setAge("example@gmail.com");
        driverResp2.setPhoneNumber("example@gmail.com");
        driverResp2.setAddress("example@gmail.com");
        driverResp2.setIdentityCard("example@gmail.com");
        driverResp2.setSex("example@gmail.com");
        driverResp2.setStatus(DriverStatus.INACTIVE);
        driverResp2.setAvgRating(new BigDecimal("4.5"));
        drivers.add(driverResp1);
        drivers.add(driverResp2);
        return drivers;
    }
}