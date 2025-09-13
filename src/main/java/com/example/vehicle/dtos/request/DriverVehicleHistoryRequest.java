package com.example.vehicle.dtos.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverVehicleHistoryRequest {
     Long historyId;

     Long vehicleId;

     Long driverId;

     LocalDateTime startDate;

     LocalDateTime endDate;

     LocalDateTime createdAt;
}
