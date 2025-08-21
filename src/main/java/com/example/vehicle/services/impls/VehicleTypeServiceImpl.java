package com.example.vehicle.services.impls;

import com.example.vehicle.dtos.request.VehicleType.VehicleTypeCreationRequest;
import com.example.vehicle.dtos.response.VehicleType.VehicleTypeResponse;
import com.example.vehicle.entities.vehicle.VehicleType;
import com.example.vehicle.mappers.VehicleTypeMapper;
import com.example.vehicle.repositories.VehicleTypeRepository;
import com.example.vehicle.services.VehicleTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleTypeServiceImpl implements VehicleTypeService {
    private final VehicleTypeRepository vehicleTypeRepository;
    private final VehicleTypeMapper vehicleTypeMapper;

    @Override
    public VehicleTypeResponse create (VehicleTypeCreationRequest request){
        log.info("VehicleType start create ...");

        VehicleType vehicleType = vehicleTypeMapper.toRequest(request);

        VehicleType saved = vehicleTypeRepository.save(vehicleType);

        return vehicleTypeMapper.toCreationResponse(saved);
    }
}
