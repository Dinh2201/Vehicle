package com.example.vehicle.dtos.response.bookinghistory;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingHistoryResponse {

     Long bookingId;

     String bookingStatus;

     DriverSummary driver;

     VehicleSummary vehicle;

     LocalDateTime updatedAt;

}
