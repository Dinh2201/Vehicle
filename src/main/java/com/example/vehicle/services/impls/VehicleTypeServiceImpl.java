package com.example.vehicle.services.impls;

import com.example.vehicle.configs.Translator;
import com.example.vehicle.dtos.request.vehicletype.VehicleTypeRequest;
import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.vehicletype.VehicleTypeResponse;
import com.example.vehicle.entities.VehicleType;
import com.example.vehicle.enums.ErrorCode;
import com.example.vehicle.enums.SuccessCode;
import com.example.vehicle.exceptions.AppException;
import com.example.vehicle.mappers.VehicleTypeMapper;
import com.example.vehicle.repositories.VehicleTypeRepository;
import com.example.vehicle.services.VehicleTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleTypeServiceImpl implements VehicleTypeService {
    private final VehicleTypeRepository vehicleTypeRepository;
    private final VehicleTypeMapper vehicleTypeMapper;

    @Override
    public ApiResponse<VehicleTypeResponse> create (VehicleTypeRequest request){
        log.info("VehicleType start create ...");

        VehicleType vehicleType = vehicleTypeMapper.toRequest(request);

        VehicleType saved = vehicleTypeRepository.save(vehicleType);

        ApiResponse<VehicleTypeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.VEHICLE_TYPE_CREATE.getCode());
        apiResponse.setData(vehicleTypeMapper.toResponse(saved));

        return apiResponse;
    }

    @Override
    public ApiResponse<List<VehicleTypeResponse>> getAllVehicleTypes(int pageNo, int pageSize, String sortBy, String sortDir) {
        log.info("VehicleType start get All VehicleTypes ...");

        log.info("Paging");
        Sort sort = sortDir.equalsIgnoreCase("ASC")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        List<VehicleType> vehicleTypes = vehicleTypeRepository.findAll(pageable).getContent();
        List<VehicleTypeResponse> responses = vehicleTypeMapper.toListResponse(vehicleTypes);

        ApiResponse<List<VehicleTypeResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.VEHICLE_TYPE_GET_ALL.getCode());
        apiResponse.setData(responses);

        log.info("VehicleType get All VehicleTypes ...");
        return apiResponse;

    }

    @Override
    public ApiResponse<VehicleTypeResponse> getVehicleTypeById(Long id) {
        log.info("VehicleType start get VehicleType by id ...");
        VehicleType vehicleType = vehicleTypeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.VEHICLE_TYPE_EXCEPTION));

        ApiResponse<VehicleTypeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.VEHICLE_TYPE_GET_BY_ID.getCode());
        apiResponse.setData(vehicleTypeMapper.toResponse(vehicleType));

        return apiResponse;
    }

    @Override
    public ApiResponse<VehicleTypeResponse> updateVehicleType(Long id, VehicleTypeRequest request) {

        log.info("VehicleType start update VehicleType by id ...");
        VehicleType vehicleType = vehicleTypeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.VEHICLE_TYPE_EXCEPTION));

        vehicleTypeMapper.updateVehicleType(vehicleType, request);
        VehicleType newVehicleType = vehicleTypeRepository.save(vehicleType);
        VehicleTypeResponse response = vehicleTypeMapper.toResponse(newVehicleType);

        ApiResponse<VehicleTypeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.VEHICLE_TYPE_UPDATE.getCode());
        apiResponse.setData(response);
        log.info("VehicleType update VehicleType by id ...");
        return apiResponse;
    }

    @Override
    public ApiResponse<Boolean> deleteVehicleType(List<Long> ids) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();

        log.info("VehicleType start delete ids ...");
        if( ids == null || ids.isEmpty() ){
            apiResponse.setData(false);
            return apiResponse;
        }

        List<VehicleType> vehicleTypes = vehicleTypeRepository.findAllById(ids);

        if( vehicleTypes.size() != ids.size() ){
            throw new AppException(ErrorCode.VEHICLE_TYPES_NOT_FOUND);
        }
        vehicleTypeRepository.deleteAll(vehicleTypes);

        apiResponse.setCode(SuccessCode.VEHICLE_TYPE_DELETE.getCode());
        apiResponse.setMessage(Translator.toLocale(SuccessCode.VEHICLE_TYPE_DELETE.getCode()));
        apiResponse.setData(true);
        return apiResponse;
    }
}
