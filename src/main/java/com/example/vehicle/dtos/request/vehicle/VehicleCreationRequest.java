package com.example.vehicle.dtos.request.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleCreationRequest {
    @NotBlank(message = "{vehicle.name.notnull}")
     String vehicleName;

    @NotBlank(message = "{license.plate.notnull}")
     String licensePlate;

    @NotBlank(message = "{status.notnull}")
     String status;

    @NotNull(message = "{signup.date.notnull}")
    LocalDate signupDate;

    Double latitude;

    Double longitude;

    @NotNull(message = "{vehicle.type.notnull}")
     long vehicleType; // chứa ID

    @NotNull(message = "{driver.notnull}")
     long driver;
}
