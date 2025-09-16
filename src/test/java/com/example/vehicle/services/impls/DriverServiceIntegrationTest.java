package com.example.vehicle.services.impls;

import com.example.vehicle.dtos.request.Driver.DriverCreationRequest;
import com.example.vehicle.mappers.DriverMapper;
import com.example.vehicle.repositories.BookingHistoryRepository;
import com.example.vehicle.repositories.DriverRepository;
import com.example.vehicle.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")  //Kích hoạt file cấu hình application-test.properties
@TestInstance(TestInstance.Lifecycle.PER_CLASS)   // dễ quản lý test state
class DriverServiceIntegrationTest {
    @Autowired DriverRepository driverRepository;
    @Autowired DriverMapper driverMapper;
    @Autowired VehicleRepository vehicleRepository;
    @Autowired BookingHistoryRepository bookingHistoryRepository;
    @Autowired
    private DriverServiceImpl driverService;

    @BeforeEach
    void setUp() {
        driverRepository.deleteAll();
    }

    @Test
    void getDriver_whenCreateDrivers_shouldReturnDrivers() {
        driverService.createDriver(new DriverCreationRequest("example1", "20" ,"0123456789", "Ho Chi Minh", "246813579", "Male", "A246813579", "ACTIVE", new BigDecimal("4.5")));

        driverService.createDriver(new DriverCreationRequest("example2", "30" ,"9876543210", "Hanoi", "987654321", "Female", "A145813579", "INACTIVE", new BigDecimal("4.0")));

        var result = driverService.getAllDrivers();
        assertFalse(result.isEmpty());
        assertEquals("example1", result.get(0).getName());
        assertEquals("example2", result.get(1).getName());
    }
}