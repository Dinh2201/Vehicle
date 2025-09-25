package com.example.vehicle.services.impls;

import com.example.vehicle.dtos.response.DriverVehicleHistoryResponse;
import com.example.vehicle.entities.DriverVehicleHistory;
import com.example.vehicle.mappers.DriverVehicleHistoryMapper;
import com.example.vehicle.repositories.DriverVehicleHistoryRepository;
import com.example.vehicle.services.DriverVehicleHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverVehicleHistoryServiceImpl implements DriverVehicleHistoryService {
    private final DriverVehicleHistoryRepository driverVehicleHistoryRepository;
    private final DriverVehicleHistoryMapper driverVehicleHistoryMapper;


    public List<DriverVehicleHistoryResponse> getAllHistory(Pageable pageable) {
        List<DriverVehicleHistory> driverVehicleHistory = driverVehicleHistoryRepository.findAll(pageable).getContent();

        List<DriverVehicleHistoryResponse> responses = driverVehicleHistoryMapper.toResponses(driverVehicleHistory);
        log.info("Driver vehicle history response");
        return responses;
    }
}
