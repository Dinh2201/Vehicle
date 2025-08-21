package com.example.vehicle.dtos.request.Driver;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverCreationRequest {

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
