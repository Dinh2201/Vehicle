package com.example.vehicle.services.impls;

import com.example.vehicle.dtos.response.driver.DriverResponse;
import com.example.vehicle.entities.Driver;
import com.example.vehicle.enums.DriverStatus;
import com.example.vehicle.mappers.DriverMapper;
import com.example.vehicle.repositories.BookingHistoryRepository;
import com.example.vehicle.repositories.DriverRepository;
import com.example.vehicle.repositories.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class DriverServiceImplTest {

    @InjectMocks   DriverServiceImpl driverServiceImpl;

   @Mock DriverRepository driverRepository;

    @Mock DriverMapper driverMapper;

    @Mock VehicleRepository vehicleRepository;

    @Mock BookingHistoryRepository bookingHistoryRepository;

    @Test
    void getAccount_shouldReturnAccounts() {
        // prepare data
        var driverEntity = buildDriverEntities();
        var driverResponses = buildDrivers();

        //Giả lập hành vi của mock

        // Giả lập hành vi của mock
        when(driverRepository.findAllByOrderByDriverIdAsc()).thenReturn(driverEntity);
        when(driverMapper.toListResponse(driverEntity)).thenReturn(driverResponses);

        // Gọi hàm và kiểm tra kết quả
        var result = driverServiceImpl.getAllDrivers();

        assertNotNull(result);
        assertEquals(driverResponses.size(), result.size());
        assertEquals(driverResponses.get(0).getDriverId(), result.get(0).getDriverId());

        // Đảm bảo method mock được gọi đúng
        verify(driverRepository, times(1)).findAllByOrderByDriverIdAsc();
        verify(driverMapper, times(1)).toListResponse(driverEntity);
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