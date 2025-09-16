package com.example.vehicle.services.impls;

import com.example.vehicle.entities.Driver;
import com.example.vehicle.entities.Vehicle;
import com.example.vehicle.enums.DriverStatus;
import com.example.vehicle.mappers.VehicleMapper;
import com.example.vehicle.repositories.DriverRepository;
import com.example.vehicle.repositories.DriverVehicleHistoryRepository;
import com.example.vehicle.repositories.VehicleRepository;
import com.example.vehicle.repositories.VehicleTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VehicleServiceImplTest
{
    @InjectMocks VehicleRepository vehicleRepository;

    @Mock VehicleMapper vehicleMapper;

    @Mock VehicleTypeRepository vehicleTypeRepository;

    @Mock DriverRepository driverRepository;

    @Mock DriverVehicleHistoryRepository driverVehicleHistoryRepository;

//    void getVehicle_shouldReturnVehicle(){
//        //prepare data
//        var Vehicle
//    }


//    private List<Vehicle> buildVehicleEntities() {
//        var vehicles = new ArrayList<Vehicle>();
//        var vehicleResp1 = new Vehicle();
//        vehicleResp1.setVehicleId(1L);
//        vehicleResp1.setUsername("example1");
//        vehicleResp1.setEmail("example@gmail.com");
//        var accountResp2 = new AccountEntity();
//        accountResp2.setId(2L);
//        accountResp2.setUsername("example2");
//        accountResp2.setEmail("example2@gmail.com");
//        accounts.add(accountResp1);
//        accounts.add(accountResp2);
//        return accounts;
//    }



}