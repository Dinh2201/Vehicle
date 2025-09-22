package com.example.vehicle.dtos.response.bookinghistory;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverSummary {
    long driverId;

    String name;

    String phoneNumber;

    String identityCard;
}
