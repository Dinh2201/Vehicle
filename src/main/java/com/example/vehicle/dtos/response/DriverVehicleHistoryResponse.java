package com.example.vehicle.dtos.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverVehicleHistoryResponse {
    Long historyId;

    Long vehicleId;

    Long driverId;

    LocalDateTime startDate;

    LocalDateTime endDate;

    LocalDateTime createdAt;

}
