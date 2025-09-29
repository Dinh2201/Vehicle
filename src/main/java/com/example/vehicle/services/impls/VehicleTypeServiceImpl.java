package com.example.vehicle.services.impls;

import com.example.vehicle.dtos.request.vehicletype.VehicleTypeRequest;
import com.example.vehicle.dtos.response.vehicletype.VehicleTypeResponse;
import com.example.vehicle.entities.VehicleType;
import com.example.vehicle.enums.ErrorCode;
import com.example.vehicle.exceptions.AppException;
import com.example.vehicle.mappers.VehicleTypeMapper;
import com.example.vehicle.repositories.VehicleTypeRepository;
import com.example.vehicle.services.VehicleTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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
    public VehicleTypeResponse create (VehicleTypeRequest request){
        log.info("VehicleType start create ...");

        VehicleType vehicleType = vehicleTypeMapper.toRequest(request);

        VehicleType saved = vehicleTypeRepository.save(vehicleType);

        return vehicleTypeMapper.toResponse(saved);
    }

    @Override
    public List<VehicleTypeResponse> getAllVehicleTypes(Pageable pageable) {
        log.info("VehicleType start get All VehicleTypes ...");
        List<VehicleType> vehicleTypes = vehicleTypeRepository.findAll(pageable).getContent();
        List<VehicleTypeResponse> responses = vehicleTypeMapper.toListResponse(vehicleTypes);
        log.info("VehicleType get All VehicleTypes ...");
        return responses;

    }

    @Override
    public VehicleTypeResponse getVehicleTypeById(Long id) {
        log.info("VehicleType start get VehicleType by id ...");

        return vehicleTypeMapper.toResponse(vehicleTypeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.VEHICLE_TYPE_EXCEPTION)));
    }

    @Override
    public VehicleTypeResponse updateVehicleType(Long id, VehicleTypeRequest request) {

        log.info("VehicleType start update VehicleType by id ...");
        VehicleType vehicleType = vehicleTypeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.VEHICLE_TYPE_EXCEPTION));

        vehicleTypeMapper.updateVehicleType(vehicleType, request);
        VehicleType newVehicleType = vehicleTypeRepository.save(vehicleType);
        VehicleTypeResponse response = vehicleTypeMapper.toResponse(newVehicleType);
        log.info("VehicleType update VehicleType by id ...");
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
            throw new AppException(ErrorCode.VEHICLE_TYPES_NOT_FOUND);
        }
        vehicleTypeRepository.deleteAll(vehicleTypes);
        return true;
    }
}
