package com.example.vehicle.components;

import com.example.vehicle.dtos.response.Vehicle.DriverDTO;
import com.example.vehicle.dtos.response.Vehicle.VehicleLocationResponse;
import com.example.vehicle.entities.Vehicle;
import com.example.vehicle.repositories.VehicleRepository;
import com.example.vehicle.services.RedisService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.type.TypeReference;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class VehicleLocationScheduler {

    private final VehicleRepository vehicleRepository;
    private final RedisService redisService;

    private final Random random = new Random();

    private void updateRedisWithVehicleList(List<Vehicle> vehicles) {
        // Sắp xếp lại danh sách vehicles theo vehicleId tăng dần
        vehicles.sort(Comparator.comparing(Vehicle::getVehicleId));
        List<VehicleLocationResponse> responseList = vehicles.stream()
                .map(v -> VehicleLocationResponse.builder()
                        .vehicleId(v.getVehicleId())
                        .vehicleName(v.getVehicleName())
                        .licensePlate(v.getLicensePlate())
                        .latitude(v.getLatitude())
                        .longitude(v.getLongitude())
                        .updatedAt(LocalDateTime.now())
                        // Lấy tài xế đầu tiên, nếu không có thì trả về null
                        .driver(v.getDrivers().stream().findFirst()
                                .map(DriverDTO::new) // Chuyển driver thành DriverDTO
                                .orElse(null))
                        .vehicleType(v.getVehicleType().getVehicleTypeId())
                        .build())
                .collect(Collectors.toList());

        redisService.setValue("vehicles::all", responseList);
    }


    @Scheduled(
            cron = "${scheduler.update-location.cron}",
            zone = "${scheduler.update-location.zone}")
    public void updateVehicleLocationsInRedis() {
        log.info("Bắt đầu cập nhật vehicle vào Redis lúc: {}", OffsetDateTime.now());

        // Lấy tất cả vehicle từ Redis
        List<Vehicle> vehicles = vehicleRepository.findActiveVehicles();

        List<Vehicle> filteredVehicles = vehicles.stream()
                .filter(vehicle -> vehicle.getDrivers().stream()
                        .anyMatch(driver -> "ACTIVE".equalsIgnoreCase(driver.getStatus().name())))
                .collect(Collectors.toList());
        // setvalue ("vehicles::all, vehicles)
//        lay trong cache ra ròi
        for (Vehicle v : filteredVehicles) {
            double latShift = (random.nextDouble() - 0.5) / 1000; // ±0.0005
            double lngShift = (random.nextDouble() - 0.5) / 1000;

            double newLat = (v.getLatitude() != null ? v.getLatitude() : 10.762622) + latShift;
            double newLng = (v.getLongitude() != null ? v.getLongitude() : 106.660172) + lngShift;

            v.setLatitude(newLat);
            v.setLongitude(newLng);
        }

        // cập nhật Redis
        updateRedisWithVehicleList(filteredVehicles); // serialize JSON và set vào Redis

        log.info("Đã cập nhật {} vehicle vào Redis lúc {}", filteredVehicles.size(), OffsetDateTime.now());
    }

    @Scheduled(
            cron = "${scheduler.update-db-location.cron}",  // `0 */5 * * * *` → Mỗi 5 phút
            zone = "${scheduler.update-location.zone}")
    @Transactional
    public void updateVehicleLocationsInDb() {
        log.info("Bắt đầu cập nhật vị trí vehicle vào DB lúc: {}", OffsetDateTime.now());

        // Lấy tất cả vehicle từ Redis
        List<VehicleLocationResponse> vehiclesFromRedis = redisService.getValue("vehicles::all", new TypeReference<>() {});

//        if (vehiclesFromRedis != null) {
//            // Cập nhật vị trí trong DB với dữ liệu lấy từ Redis
//            List<Vehicle> vehiclesToUpdate = vehiclesFromRedis.stream()
//                    .map(dto -> {
//                        Vehicle v = vehicleRepository.findById(dto.getVehicleId()).orElse(new Vehicle());
//                        v.setLatitude(dto.getLatitude());
//                        v.setLongitude(dto.getLongitude());
//                        return v;
//                    })
//                    .collect(Collectors.toList());
        if (vehiclesFromRedis != null) {
            List<Vehicle> vehiclesToUpdate = vehiclesFromRedis.stream()
                    .map(dto -> vehicleRepository.findById(dto.getVehicleId()).orElse(null))
                    .filter(Objects::nonNull)
                    .filter(vehicle -> vehicle.getDrivers().stream()
                            .anyMatch(driver -> "ACTIVE".equalsIgnoreCase(driver.getStatus().name())))
                    .map(vehicle -> {
                        VehicleLocationResponse dto = vehiclesFromRedis.stream()
                                .filter(d -> d.getVehicleId().equals(vehicle.getVehicleId()))
                                .findFirst()
                                .orElse(null);
                        if (dto != null) {
                            vehicle.setLatitude(dto.getLatitude());
                            vehicle.setLongitude(dto.getLongitude());
                        }
                        return vehicle;
                    })
                    .collect(Collectors.toList());

            // Cập nhật DB
            vehicleRepository.saveAll(vehiclesToUpdate);
            log.info("Đã cập nhật {} vehicle vào DB lúc {}", vehiclesToUpdate.size(), OffsetDateTime.now());

            // Sau khi cập nhật DB, cập nhật lại Redis với dữ liệu mới nhất
            updateRedisWithVehicleList(vehiclesToUpdate);  // Cập nhật Redis với danh sách vehicles mới
        }
    }



}
