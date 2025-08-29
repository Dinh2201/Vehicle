package com.example.vehicle.mappers;

import com.example.vehicle.dtos.response.Vehicle.DriverDTO;
import com.example.vehicle.dtos.response.Vehicle.VehicleResponse;
import com.example.vehicle.dtos.response.Vehicle.VehicleResponseNoVehicleType;
import com.example.vehicle.dtos.response.Vehicle.VehicleTypeDTO;
import com.example.vehicle.entities.vehicle.Driver;
import com.example.vehicle.entities.vehicle.Vehicle;
import com.example.vehicle.entities.vehicle.VehicleType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
//    @Named("vehicleTypeMapper")
    @Mapping(source = "vehicleId", target = "vehicleId")
    @Mapping(source = "vehicleType", target = "vehicleType")
    @Mapping(target = "driver", expression = "java(mapSingleDriver(vehicle.getDrivers()))")
    VehicleResponse toResponse(Vehicle vehicle);

    DriverDTO toDto(Driver driver);

    @Mapping(target = "driver", expression = "java(mapSingleDriver(vehicle.getDrivers()))")
    VehicleResponseNoVehicleType toResponseNoVehicleType(Vehicle vehicle);


   // Hàm map VehicleType -> long

    default long mapVehicleTypeToId(VehicleType vehicleType) {
        return vehicleType != null ? vehicleType.getVehicleTypeId() : 0L;
    }

    // Chỉ lấy 1 driver duy nhất từ Set
    default DriverDTO mapSingleDriver(Set<Driver> drivers) {
        if (drivers == null || drivers.isEmpty()) {
            return null;
        }
        Driver driver = drivers.iterator().next();
        return toDto(driver);
    }

}
