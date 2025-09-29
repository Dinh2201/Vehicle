package com.example.vehicle.mappers;

import com.example.vehicle.dtos.response.DriverNotificationResponse;
import com.example.vehicle.entities.DriverNotification;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DriverNotificationMapper {

    List<DriverNotificationResponse> toResponseList(List<DriverNotification> driverNotificationList);
}
