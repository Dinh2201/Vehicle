package com.example.vehicle.mappers;

import com.example.vehicle.dtos.request.VehicleType.VehicleTypeCreationRequest;
import com.example.vehicle.dtos.response.VehicleType.VehicleTypeResponse;
import com.example.vehicle.entities.VehicleType;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = VehicleMapper.class)
public interface VehicleTypeMapper {


    VehicleType toRequest(VehicleTypeCreationRequest request); //toRequest dùng để chuyển dữ liệu từ request vào entity để lưu vào DB.

    @Mapping(source = "vehicles", target = "vehicles")
    VehicleTypeResponse toResponse(VehicleType vehicleType); //toResponse dùng để chuyển dữ liệu từ entity sau khi lưu vào DB thành response để trả lại cho client.


    void updateVehicleType(@MappingTarget VehicleType vehicleType, VehicleTypeCreationRequest request);
}
