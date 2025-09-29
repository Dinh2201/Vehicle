package com.example.vehicle.services.impls;

import com.example.vehicle.entities.Driver;
import com.example.vehicle.entities.Vehicle;
import com.example.vehicle.enums.ErrorCode;
import com.example.vehicle.exceptions.AppException;
import com.example.vehicle.mappers.DriverMapper;
import com.example.vehicle.repositories.BookingHistoryRepository;
import com.example.vehicle.repositories.DriverRepository;
import com.example.vehicle.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverServiceDeleteTest {
    @InjectMocks
    DriverServiceImpl driverServiceImpl;

    @Mock
    DriverRepository driverRepository;

    @Mock
    DriverMapper driverMapper;

    @Mock
    VehicleRepository vehicleRepository;

    @Mock
    BookingHistoryRepository bookingHistoryRepository;

    @Test
    void deleteDriver_whenSomeDriversNotExist_shouldThrowException() {
        // Test trường hợp một số driver không tồn tại
        List<Long> ids = List.of(1L, 2L, 3L); // Giả sử 3L không tồn tại trong cơ sở dữ liệu.

        // Mô phỏng hành vi của driverRepository
        List<Driver> drivers = buildDriverEntities();

        when(driverRepository.findAllById(ids)).thenReturn(drivers.subList(0,2)); // Chỉ trả về driver 1 và 2

        // Kiểm tra nếu exception được ném ra khi số lượng không khớp
        AppException exception = assertThrows(AppException.class, () -> {
            driverServiceImpl.deleteDriver(ids);
        });

        assertEquals(ErrorCode.DRIVERS_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    void deleteDriver_whenAllDriversExist_shouldDeleteDrivers() {
        // Test trường hợp tất cả drivers đều tồn tại và sẽ bị xóa.
        List<Long> ids = List.of(1L, 2L);

        // Thay vì tạo Driver và Vehicle trực tiếp, sử dụng phương thức buildDriverEntities để tạo dữ liệu
        List<Driver> drivers = buildDriverEntities();

        // Mô phỏng hành vi của các repository
        when(driverRepository.findAllById(ids)).thenReturn(drivers);

        // Gọi phương thức delete
        boolean result = driverServiceImpl.deleteDriver(ids);

        // Kiểm tra các phương thức đã được gọi đúng cách
        assertTrue(result, "The method should return true when drivers are deleted.");

        // Kiểm tra xem vehicle đã được cập nhật đúng chưa (liên kết driver - vehicle đã bị xóa)
        verify(vehicleRepository, times(1)).save(drivers.get(0).getVehicle()); // Save vehicle1
        verify(vehicleRepository, times(1)).save(drivers.get(1).getVehicle()); // Save vehicle2
        verify(driverRepository, times(1)).deleteAll(drivers); // Xóa các driver
    }

    private List<Driver> buildDriverEntities() {
        List<Driver> drivers = new ArrayList<>();

        Driver driver1 = new Driver();
        driver1.setDriverId(1L);
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setDriver(driver1);
        driver1.setVehicle(vehicle1);
        drivers.add(driver1);

        Driver driver2 = new Driver();
        driver2.setDriverId(2L);
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setDriver(driver2);
        driver2.setVehicle(vehicle2);
        drivers.add(driver2);

        return drivers;
    }
}