package com.example.vehicle.dtos.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {
    private long driverId;

    private String name;

    private String age;

    private String phoneNumber;

    private String address;

    private String identityCard;

    private String sex;

    private String driverLicense;

    private String status;

    private BigDecimal avgRating;
}
