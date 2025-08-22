package com.example.vehicle.services.impls;

import com.example.vehicle.dtos.request.Driver.DriverCreationRequest;
import com.example.vehicle.dtos.response.Driver.DriverResponse;
import com.example.vehicle.entities.vehicle.Driver;
import com.example.vehicle.mappers.DriverMapper;
import com.example.vehicle.repositories.DriverRepository;
import com.example.vehicle.services.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Override
    public DriverResponse createDriver (DriverCreationRequest request){
        log.info("VehicleType start create ...");

        Driver driver = driverMapper.toRequest(request);
        Driver response = driverRepository.save(driver);
        return driverMapper.toResponse(response);
    }

}
