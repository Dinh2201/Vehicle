package com.example.vehicle.dtos.response.bookinghistory;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleSummary {
    Long vehicleId;

    String vehicleName;

    String licensePlate;
}
