package com.example.vehicle.services.impls;

import com.example.vehicle.dtos.request.VehicleCreationRequest;
import com.example.vehicle.entities.vehicle.DriverEntity;
import com.example.vehicle.entities.vehicle.VehicleEntity;
import com.example.vehicle.entities.vehicle.VehicleTypeEntity;
import com.example.vehicle.mappers.VehicleMapper;
import com.example.vehicle.repositories.DriverRepository;
import com.example.vehicle.repositories.VehicleRepository;
import com.example.vehicle.repositories.VehicleTypeRepository;
import com.example.vehicle.services.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final DriverRepository driverRepository;


    @Override
    public VehicleEntity createVehicle(VehicleCreationRequest request){
        log.info("Start create vehicle ...");
//        VehicleEntity vehicle =vehicleMapper.vehicleToRequest(request);
//        return vehicleRepository.save(vehicle);

        VehicleTypeEntity vehicleType = vehicleTypeRepository.findById(request.getVehicleType())
                .orElseThrow(() -> new RuntimeException("VehicleType not found"));

        DriverEntity driver = driverRepository.findById(request.getDriver())
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        VehicleEntity vehicle = VehicleEntity.builder()
                .vehicleName(request.getVehicleName())
                .licensePlate(request.getLicensePlate())
                .status(request.getStatus())
                .signupDate(request.getSignupDate())
                .vehicleType(vehicleType)
                .driver(driver)
                .build();

        return vehicleRepository.save(vehicle);
    }
}
