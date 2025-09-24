package com.example.vehicle.dtos.response;

import com.example.vehicle.dtos.response.bookinghistory.DriverSummary;
import com.example.vehicle.dtos.response.bookinghistory.VehicleSummary;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    LocalDateTime startDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    LocalDateTime endDate;

}
