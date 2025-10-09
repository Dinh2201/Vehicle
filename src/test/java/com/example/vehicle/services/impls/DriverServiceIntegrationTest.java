package com.example.vehicle.services.impls;

import com.example.vehicle.mappers.DriverMapper;
import com.example.vehicle.repositories.BookingHistoryRepository;
import com.example.vehicle.repositories.DriverRepository;
import com.example.vehicle.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")  //Kích hoạt file cấu hình application-test.properties
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DriverServiceIntegrationTest {
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    DriverMapper driverMapper;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    BookingHistoryRepository bookingHistoryRepository;
    @Autowired
    private DriverServiceImpl driverService;

    @BeforeEach
    void setUp() {
        driverRepository.deleteAll();
    }

//    @Test
//    void getDriver_whenCreateDrivers_shouldReturnDrivers() {
//        driverService.createDriver(new DriverRequest("example1", "20" ,"0123456789", "Ho Chi Minh", "246813579", "Male", "A246813579", "ACTIVE", new BigDecimal("4.5")));
//
//        driverService.createDriver(new DriverRequest("example2", "30" ,"9876543210", "Hanoi", "987654321", "Female", "A145813579", "INACTIVE", new BigDecimal("4.0")));
//// Gọi getAllDrivers với Pageable (Page 0, size 10)
//        Pageable pageable = PageRequest.of(0, 10);
//        var result = driverService.getAllDrivers(pageable);
//        assertFalse(result.getData().isEmpty());
//        assertEquals(2, result.getData().size());
//        assertEquals("example1", result.getData().get(0).getName());
//        assertEquals("example2", result.getData().get(1).getName());
//    }
}