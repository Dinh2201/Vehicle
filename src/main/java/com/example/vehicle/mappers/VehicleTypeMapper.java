package com.example.vehicle.mappers;

import com.example.vehicle.dtos.request.vehicletype.VehicleTypeRequest;
import com.example.vehicle.dtos.response.vehicletype.VehicleTypeResponse;
import com.example.vehicle.entities.VehicleType;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = VehicleMapper.class)
public interface VehicleTypeMapper {


    VehicleType toRequest(VehicleTypeRequest request); //toRequest dùng để chuyển dữ liệu từ request vào entity để lưu vào DB.

    @Mapping(source = "vehicles", target = "vehicles")
    VehicleTypeResponse toResponse(VehicleType vehicleType); //toResponse dùng để chuyển dữ liệu từ entity sau khi lưu vào DB thành response để trả lại cho client.

    List<VehicleTypeResponse> toListResponse(List<VehicleType> vehicleTypes);


    void updateVehicleType(@MappingTarget VehicleType vehicleType, VehicleTypeRequest request);
}
