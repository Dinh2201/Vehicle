package com.example.vehicle.dtos.response.Vehicle;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverDTO {
     long driverId;

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
