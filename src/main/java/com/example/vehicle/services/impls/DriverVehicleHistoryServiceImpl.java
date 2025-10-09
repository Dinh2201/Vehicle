package com.example.vehicle.services.impls;

import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.DriverVehicleHistoryResponse;
import com.example.vehicle.entities.DriverVehicleHistory;
import com.example.vehicle.enums.SuccessCode;
import com.example.vehicle.mappers.DriverVehicleHistoryMapper;
import com.example.vehicle.repositories.DriverVehicleHistoryRepository;
import com.example.vehicle.services.DriverVehicleHistoryService;
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
public class DriverVehicleHistoryServiceImpl implements DriverVehicleHistoryService {
    private final DriverVehicleHistoryRepository driverVehicleHistoryRepository;
    private final DriverVehicleHistoryMapper driverVehicleHistoryMapper;


    public ApiResponse<List<DriverVehicleHistoryResponse>> getAllHistory(int pageNo, int pageSize, String sortBy, String sortDir) {

        log.info("Paging");
        Sort sort = sortDir.equalsIgnoreCase("ASC")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        List<DriverVehicleHistory> driverVehicleHistory = driverVehicleHistoryRepository.findAll(pageable).getContent();

        List<DriverVehicleHistoryResponse> responses = driverVehicleHistoryMapper.toResponses(driverVehicleHistory);

        ApiResponse<List<DriverVehicleHistoryResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.DRIVER_VEHICLE_HISTORY.getCode());
        apiResponse.setData(responses);
        log.info("Driver vehicle history response");
        return apiResponse;
    }
}
