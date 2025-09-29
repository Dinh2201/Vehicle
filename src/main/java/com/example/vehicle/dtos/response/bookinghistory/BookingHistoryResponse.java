package com.example.vehicle.dtos.response.bookinghistory;

import com.fasterxml.jackson.annotation.JsonFormat;
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

     @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
     LocalDateTime updatedAt;

}
