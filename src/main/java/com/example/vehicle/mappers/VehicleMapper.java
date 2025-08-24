package com.example.vehicle.mappers;

import com.example.vehicle.dtos.response.Vehicle.DriverDTO;
import com.example.vehicle.dtos.response.Vehicle.VehicleResponse;
import com.example.vehicle.dtos.response.Vehicle.VehicleTypeDTO;
import com.example.vehicle.entities.vehicle.Driver;
import com.example.vehicle.entities.vehicle.Vehicle;
import com.example.vehicle.entities.vehicle.VehicleType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    @Mapping(source = "vehicleId", target = "vehicleId")
    VehicleResponse toResponse(Vehicle vehicle);

    default List<Long> mapDrivers(Set<Driver> drivers) {
        return drivers.stream().map(Driver::getDriverId).collect(Collectors.toList());
    }

    @Mapping(source = "vehicleTypeId", target = "vehicleTypeId")
    VehicleTypeDTO toDto(VehicleType vehicleType);

    DriverDTO toDto(Driver driver);

}
