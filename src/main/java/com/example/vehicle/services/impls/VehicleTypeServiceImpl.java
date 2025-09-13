package com.example.vehicle.services.impls;

import com.example.vehicle.dtos.request.VehicleType.VehicleTypeCreationRequest;
import com.example.vehicle.dtos.response.VehicleType.VehicleTypeResponse;
import com.example.vehicle.entities.VehicleType;
import com.example.vehicle.mappers.VehicleTypeMapper;
import com.example.vehicle.repositories.VehicleTypeRepository;
import com.example.vehicle.services.VehicleTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        return vehicleTypeMapper.toResponse(saved);
    }

    @Override
    public List<VehicleTypeResponse> getAllVehicleTypes() {
        log.info("VehicleType start get All VehicleTypes ...");
        List<VehicleType> vehicleTypes = vehicleTypeRepository.findAllByOrderByVehicleTypeIdAsc();
        List<VehicleTypeResponse> responses = vehicleTypes.stream()
                .map(
                        vehicleTypeMapper::toResponse
                )
                .collect(Collectors.toList());
        return responses;

    }

    @Override
    public VehicleTypeResponse getVehicleTypeById(Long id) {
        log.info("VehicleType start get VehicleType by id ...");

        return vehicleTypeMapper.toResponse(vehicleTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("VehicleType not found with id " + id)));
    }

    @Override
    public VehicleTypeResponse updateVehicleType(Long id, VehicleTypeCreationRequest request) {

        log.info("VehicleType start update VehicleType by id ...");
        VehicleType vehicleType = vehicleTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("VehicleType not found with id " + id));

        vehicleTypeMapper.updateVehicleType(vehicleType, request);
        VehicleType newVehicleType = vehicleTypeRepository.save(vehicleType);
        VehicleTypeResponse response = vehicleTypeMapper.toResponse(newVehicleType);
        return response;
    }

    @Override
    public boolean deleteVehicleType(List<Long> ids) {
        log.info("VehicleType start delete ids ...");
        if( ids == null || ids.isEmpty() ){
            return false;
        }

        List<VehicleType> vehicleTypes = vehicleTypeRepository.findAllById(ids);

        if( vehicleTypes.size() != ids.size() ){
            throw new RuntimeException("Some vehicle types do not exist");
        }
        vehicleTypeRepository.deleteAll(vehicleTypes);
        return true;
    }
}
