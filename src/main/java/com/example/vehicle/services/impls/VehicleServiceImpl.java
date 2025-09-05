package com.example.vehicle.services.impls;

import com.example.vehicle.dtos.request.Vehicle.VehicleCreationRequest;
import com.example.vehicle.dtos.request.Vehicle.VehicleUpdateRequest;
import com.example.vehicle.dtos.response.Vehicle.VehicleResponse;
import com.example.vehicle.entities.vehicle.Driver;
import com.example.vehicle.entities.vehicle.Vehicle;
import com.example.vehicle.entities.vehicle.VehicleType;
import com.example.vehicle.exceptions.AppException;
import com.example.vehicle.exceptions.ErrorCode;
import com.example.vehicle.mappers.VehicleMapper;
import com.example.vehicle.repositories.DriverRepository;
import com.example.vehicle.repositories.VehicleRepository;
import com.example.vehicle.repositories.VehicleTypeRepository;
import com.example.vehicle.services.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final DriverRepository driverRepository;


//    @Override
//    public Vehicle createVehicle(VehicleCreationRequest request) {
//        log.info("Start creating vehicle...");
//
//        // Tìm VehicleType theo ID
//        VehicleTypeEntity vehicleType = vehicleTypeRepository.findById(request.getVehicleType())
//                .orElseThrow(() -> new RuntimeException("VehicleType not found with ID: " + request.getVehicleType()));
//
//        // Tìm Driver theo ID
//        Set<Driver> drivers = request.getDrivers().stream()
//                .map(driverId -> driverRepository.findById(driverId)
//                        .orElseThrow(() -> new RuntimeException("Driver not found with ID: " + driverId)))
//                .collect(Collectors.toSet());
//
//        // Tạo entity
//        Vehicle vehicle = Vehicle.builder()
//                .vehicleName(request.getVehicleName())
//                .licensePlate(request.getLicensePlate())
//                .status(request.getStatus())
//                .signupDate(request.getSignupDate())
//                .vehicleType(vehicleType)
//                .drivers(drivers)
//                .build();
//
//        return vehicleRepository.save(vehicle);
//    }

    // Hàm random số thực trong khoảng [min, max]
    private double getRandomInRange(double min, double max) {
        return min + (Math.random() * (max - min));
    }

    @Override
    public VehicleResponse createVehicle(VehicleCreationRequest request) {
        log.info("Start creating vehicle...");

        if(vehicleRepository.existsByLicensePlate(request.getLicensePlate())) {
            throw new AppException(ErrorCode.VEHICLE_LICENSE_PLATE_EXCEPTION);
        }

        VehicleType vehicleType = vehicleTypeRepository.findById(request.getVehicleType())
                .orElseThrow(() -> new AppException(ErrorCode.VEHICLE_TYPE_EXCEPTION));

        Driver driver = driverRepository.findById(request.getDriver())
                .orElseThrow(() -> new AppException(ErrorCode.DRIVER_EXCEPTION));

        Set<Driver> drivers = new HashSet<>();
        drivers.add(driver);

        // ==== Random vị trí (lat/lon) trong khoảng giới hạn ====
        double latitude = getRandomInRange(10.75, 10.85);     // Vĩ độ HCM
        double longitude = getRandomInRange(106.60, 106.75);  // Kinh độ HCM
        // ======================================================

        Vehicle vehicle = Vehicle.builder()
                .vehicleName(request.getVehicleName())
                .licensePlate(request.getLicensePlate())
                .status(request.getStatus())
                .signupDate(request.getSignupDate().atTime(LocalTime.now()))
                .latitude(latitude)
                .longitude(longitude)
                .vehicleType(vehicleType)
                .drivers(drivers)
                .build();

        Vehicle response = vehicleRepository.save(vehicle);
        return vehicleMapper.toResponse(response);
    }


    @Override
    public List<VehicleResponse> getAllVehicles() {
        log.info("Start get all vehicle ...");
        List<Vehicle> vehicles = vehicleRepository.findAllByOrderByVehicleIdAsc();
        List<VehicleResponse> responses = vehicles.stream()
                .map(vehicleMapper::toResponse)
                .collect(Collectors.toList());
        return responses;
    }

    @Override
    public VehicleResponse getVehicleById(Long id) {
        return vehicleMapper.toResponse(vehicleRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.VEHICLE_EXCEPTION )));
    }

    @Override
    public VehicleResponse updateVehicle(Long id, VehicleUpdateRequest vehicleUpdateRequest) {
        log.info("Start update vehicle ...");

        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.VEHICLE_EXCEPTION ));

        vehicle.setVehicleName(vehicleUpdateRequest.getVehicleName());
        vehicle.setLicensePlate(vehicleUpdateRequest.getLicensePlate());
        vehicle.setStatus(vehicleUpdateRequest.getStatus());
        if (vehicleUpdateRequest.getSignupDate() != null) {
            vehicle.setSignupDate(vehicleUpdateRequest.getSignupDate().atTime(LocalTime.now()));
        }

        log.info("Cập nhật Vehicle Type");
        VehicleType vehicleType = vehicleTypeRepository.findById(vehicleUpdateRequest.getVehicleType()).orElseThrow(() -> new AppException(ErrorCode.VEHICLE_TYPE_EXCEPTION));
        vehicle.setVehicleType(vehicleType);

        log.info("Cập nhật drivers nếu có");
        Driver driver = driverRepository.findById(vehicleUpdateRequest.getDriver())
                .orElseThrow(() -> new AppException(ErrorCode.DRIVER_EXCEPTION));

        Set<Driver> drivers = new HashSet<>();
        drivers.add(driver);
        vehicle.setDrivers(drivers);

        log.info("Lưu lại vehicle đã cập nhật");
        Vehicle response = vehicleRepository.save(vehicle);

        return vehicleMapper.toResponse(response);

    }

    @Override
    public VehicleResponse updateVehicleLocation(Long id) {
        log.info("Start update vehicle location ...");
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + id));

        // ==== Random vị trí (lat/lon) trong khoảng giới hạn ====
        double latitude = getRandomInRange(10.75, 10.85);     // Vĩ độ HCM
        double longitude = getRandomInRange(106.60, 106.75);  // Kinh độ HCM
        // ======================================================
        vehicle.setLatitude(latitude);
        vehicle.setLongitude(longitude);

         vehicleRepository.save(vehicle);
        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toResponse(updatedVehicle);
    }

    @Override
    public boolean deleteVehicle(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }

        log.info("Lấy danh sách thực tế các vehicle tồn tại");
        List<Vehicle> vehicles = vehicleRepository.findAllById(ids);

        log.info("So sánh số lượng");
        if (vehicles.size() != ids.size()) {
            throw new RuntimeException("Some vehicle IDs do not exist.");
        }

        vehicleRepository.deleteAll(vehicles);
        return true;
    }

}
