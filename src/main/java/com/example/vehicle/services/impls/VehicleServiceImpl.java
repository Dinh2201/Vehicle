package com.example.vehicle.services.impls;

import com.example.vehicle.configs.Translator;
import com.example.vehicle.dtos.request.vehicle.VehicleCreationRequest;
import com.example.vehicle.dtos.request.vehicle.VehicleUpdateRequest;
import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.vehicle.VehicleResponse;
import com.example.vehicle.entities.Driver;
import com.example.vehicle.entities.DriverVehicleHistory;
import com.example.vehicle.entities.Vehicle;
import com.example.vehicle.entities.VehicleType;
import com.example.vehicle.enums.SuccessCode;
import com.example.vehicle.enums.VehicleStatus;
import com.example.vehicle.exceptions.AppException;
import com.example.vehicle.enums.ErrorCode;
import com.example.vehicle.mappers.VehicleMapper;
import com.example.vehicle.repositories.DriverRepository;
import com.example.vehicle.repositories.DriverVehicleHistoryRepository;
import com.example.vehicle.repositories.VehicleRepository;
import com.example.vehicle.repositories.VehicleTypeRepository;
import com.example.vehicle.services.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final DriverRepository driverRepository;
    private final DriverVehicleHistoryRepository driverVehicleHistoryRepository;



    // Hàm random số thực trong khoảng [min, max]
    private double getRandomInRange(double min, double max) {
        return min + (Math.random() * (max - min));
    }

    @Override
    public ApiResponse<VehicleResponse> createVehicle(VehicleCreationRequest request) {
        log.info("Start creating vehicle...");

        if(vehicleRepository.existsByLicensePlate(request.getLicensePlate())) {
            throw new AppException(ErrorCode.VEHICLE_LICENSE_PLATE_EXCEPTION);
        }

        VehicleType vehicleType = vehicleTypeRepository.findById(request.getVehicleType())
                .orElseThrow(() -> new AppException(ErrorCode.VEHICLE_TYPE_EXCEPTION));

        Driver driver = driverRepository.findById(request.getDriver())
                .orElseThrow(() -> new AppException(ErrorCode.DRIVER_EXCEPTION));


        // ==== Random vị trí (lat/lon) trong khoảng giới hạn ====
        Double latitude = null;
        Double longitude = null;

       if ("ACTIVE".equalsIgnoreCase(request.getStatus()) && "ACTIVE".equalsIgnoreCase(driver.getStatus().name())) {
        // ==== Random vị trí (lat/lon) trong khoảng giới hạn ====
         latitude = getRandomInRange(10.75, 10.85);     // Vĩ độ HCM
         longitude = getRandomInRange(106.60, 106.75);  // Kinh độ HCM
        // ======================================================
    }

        VehicleStatus statusEnum = VehicleStatus.valueOf(request.getStatus().toUpperCase());

        Vehicle vehicle = Vehicle.builder()
                .vehicleName(request.getVehicleName())
                .licensePlate(request.getLicensePlate())
                .status(statusEnum)
                .signupDate(request.getSignupDate().atTime(LocalTime.now()))
                .latitude(latitude)
                .longitude(longitude)
                .vehicleType(vehicleType)
                .driver(driver)
                .build();

        Vehicle response = vehicleRepository.save(vehicle);


        DriverVehicleHistory driverVehicleHistory = new DriverVehicleHistory();
        driverVehicleHistory.setDriver(driver);
        driverVehicleHistory.setVehicle(response);
        driverVehicleHistory.setStartDate(LocalDateTime.now());
        driverVehicleHistoryRepository.save(driverVehicleHistory);
        log.info("Vehicle and driver history created successfully");

        ApiResponse<VehicleResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.VEHICLE_CREATE.getCode());
        apiResponse.setMessage(Translator.toLocale(SuccessCode.VEHICLE_CREATE.getCode()));
        apiResponse.setData(vehicleMapper.toResponse(response));

        return apiResponse;
    }


    @Override
    public ApiResponse<List<VehicleResponse>> getAllVehicles(int pageNo, int pageSize, String sortBy, String sortDir) {
        log.info("Start get all vehicle ...");

        log.info("Paging");
        Sort sort = sortDir.equalsIgnoreCase("ASC")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        List<Vehicle> vehicles = vehicleRepository.findAll(pageable).getContent();

        List<VehicleResponse> responses = vehicleMapper.toListResponse(vehicles);

        ApiResponse<List<VehicleResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.VEHICLE_GET_ALL.getCode());
        apiResponse.setData(responses);

        log.info("Vehicles get all successfully");
        return apiResponse;
    }

    @Override
    public ApiResponse<VehicleResponse> getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.VEHICLE_EXCEPTION));

        ApiResponse<VehicleResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.VEHICLE_GET_BY_ID.getCode());
        apiResponse.setData(vehicleMapper.toResponse(vehicle));
        return apiResponse;
    }

    @Override
    public ApiResponse<VehicleResponse> updateVehicle(Long id, VehicleUpdateRequest vehicleUpdateRequest) {
        log.info("Start update vehicle ...");

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.VEHICLE_EXCEPTION));


        vehicle.setVehicleName(vehicleUpdateRequest.getVehicleName());
        vehicle.setLicensePlate(vehicleUpdateRequest.getLicensePlate());

        VehicleStatus statusEnum = VehicleStatus.valueOf(vehicleUpdateRequest.getStatus().toUpperCase());
        vehicle.setStatus(statusEnum);

        if ("INACTIVE".equals(vehicleUpdateRequest.getStatus())) {
            vehicle.setLatitude(null);
            vehicle.setLongitude(null);
        }

        if (vehicleUpdateRequest.getSignupDate() != null) {
            vehicle.setSignupDate(vehicleUpdateRequest.getSignupDate().atTime(LocalTime.now()));
        }

        log.info("Cập nhật Vehicle Type");
        VehicleType vehicleType = vehicleTypeRepository.findById(vehicleUpdateRequest.getVehicleType())
                .orElseThrow(() -> new AppException(ErrorCode.VEHICLE_TYPE_EXCEPTION));
        vehicle.setVehicleType(vehicleType);

        log.info("Cập nhật driver nếu có");
        Driver newDriver = driverRepository.findById(vehicleUpdateRequest.getDriver())
                .orElseThrow(() -> new AppException(ErrorCode.DRIVER_EXCEPTION));

        log.info("Lấy danh sách các tài xế (drivers) hiện tại của xe (vehicle)");
        Driver oldDriver = vehicle.getDriver();

        log.info("Nếu driver hiện tại khác với driver mới");
        if (oldDriver == null || oldDriver.getDriverId() != newDriver.getDriverId()) {
            // Lưu lịch sử cho tất cả các driver hiện tại
            if ( oldDriver != null) {
                DriverVehicleHistory oldHistory = driverVehicleHistoryRepository.findTopByDriverAndVehicleAndEndDateIsNull(oldDriver, vehicle);
                if (oldHistory != null) {
                    oldHistory.setEndDate(LocalDateTime.now());  // Cập nhật end_date cho driver cũ
                    driverVehicleHistoryRepository.save(oldHistory);  // Lưu cập nhật
                }
            }

                // Tạo bản ghi mới cho driver mới
                DriverVehicleHistory newHistory = new DriverVehicleHistory();
                newHistory.setDriver(newDriver);
                newHistory.setVehicle(vehicle);
                newHistory.setStartDate(LocalDateTime.now()); // Start date cho driver mới
                driverVehicleHistoryRepository.save(newHistory);

            vehicle.setDriver(newDriver);
        }

        log.info("Lưu lại vehicle đã cập nhật");
        Vehicle response = vehicleRepository.save(vehicle);

        ApiResponse<VehicleResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.VEHICLE_UPDATE.getCode());
        apiResponse.setData(vehicleMapper.toResponse(response));
        return apiResponse;

    }

    @Override
    public ApiResponse<Boolean> deleteVehicle(List<Long> ids) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        if (ids == null || ids.isEmpty()) {
            apiResponse.setData(false);
            return apiResponse;
        }

        log.info("Lấy danh sách thực tế các vehicle tồn tại");
        List<Vehicle> vehicles = vehicleRepository.findAllById(ids);

        log.info("So sánh số lượng");
        if (vehicles.size() != ids.size()) {
            throw new AppException(ErrorCode.VEHICLES_NOT_FOUND);
        }

        vehicleRepository.deleteAll(vehicles);
        apiResponse.setCode(SuccessCode.VEHICLE_DELETE.getCode());
        apiResponse.setMessage(Translator.toLocale(SuccessCode.VEHICLE_DELETE.getCode()));
        apiResponse.setData(true);
        return apiResponse;
    }

}
