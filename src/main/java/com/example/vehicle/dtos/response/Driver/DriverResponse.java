package com.example.vehicle.dtos.response.Driver;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverResponse {
    String name;

    String age;

    String phoneNumber;

    String address;

    String identityCard;

    String sex;

    String driverLicense;

    String status;

    BigDecimal avgRating;
}
