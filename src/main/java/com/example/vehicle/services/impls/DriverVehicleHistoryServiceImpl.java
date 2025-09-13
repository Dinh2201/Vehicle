package com.example.vehicle.services.impls;

import com.example.vehicle.dtos.response.DriverVehicleHistoryResponse;
import com.example.vehicle.entities.DriverVehicleHistory;
import com.example.vehicle.mappers.DriverVehicleHistoryMapper;
import com.example.vehicle.repositories.DriverVehicleHistoryRepository;
import com.example.vehicle.services.DriverVehicleHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverVehicleHistoryServiceImpl implements DriverVehicleHistoryService {
    private final DriverVehicleHistoryRepository driverVehicleHistoryRepository;
    private final DriverVehicleHistoryMapper driverVehicleHistoryMapper;

    public List<DriverVehicleHistoryResponse> getAllHistory() {
        List<DriverVehicleHistory> driverVehicleHistory = driverVehicleHistoryRepository.findAll();
        List<DriverVehicleHistoryResponse> responses = driverVehicleHistory.stream()
                .map(driverVehicleHistoryMapper::toResponse)
                .collect(Collectors.toList());
        return responses;
    }
}
