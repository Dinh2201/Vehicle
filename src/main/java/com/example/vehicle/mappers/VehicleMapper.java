package com.example.vehicle.mappers;

import com.example.vehicle.dtos.response.driver.DriverResponse;
import com.example.vehicle.dtos.response.vehicle.DriverDTO;
import com.example.vehicle.dtos.response.vehicle.VehicleResponse;
import com.example.vehicle.dtos.response.vehicle.VehicleResponseNoVehicleType;
import com.example.vehicle.entities.Driver;
import com.example.vehicle.entities.Vehicle;
import com.example.vehicle.entities.VehicleType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
//    @Named("vehicleTypeMapper")
    @Mapping(source = "vehicleId", target = "vehicleId")
    @Mapping(source = "vehicleType", target = "vehicleType")
    @Mapping(source = "driver", target = "driver")
    VehicleResponse toResponse(Vehicle vehicle);

    List<VehicleResponse> toListResponse(List<Vehicle> driver);

    DriverDTO toDto(Driver driver);

    @Mapping(target = "driver", expression = "java(vehicle.getDriver() != null ? toDto(vehicle.getDriver()) : null)")
    VehicleResponseNoVehicleType toResponseNoVehicleType(Vehicle vehicle);

   // Hàm map VehicleType -> long
    default long mapVehicleTypeToId(VehicleType vehicleType) {
        return vehicleType != null ? vehicleType.getVehicleTypeId() : 0L;
    }



}
