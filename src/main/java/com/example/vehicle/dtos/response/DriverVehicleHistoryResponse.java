package com.example.vehicle.dtos.response;

import com.example.vehicle.dtos.response.bookinghistory.DriverSummary;
import com.example.vehicle.dtos.response.bookinghistory.VehicleSummary;
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

    VehicleSummary vehicle;

    DriverSummary driver;

    LocalDateTime startDate;

    LocalDateTime endDate;

}
