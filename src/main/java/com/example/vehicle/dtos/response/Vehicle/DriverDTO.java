package com.example.vehicle.dtos.response.Vehicle;

import com.example.vehicle.entities.vehicle.Driver;
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

     public DriverDTO(Driver driver) {
          this.driverId = driver.getDriverId();
          this.name = driver.getName();
          this.age = driver.getAge();
          this.phoneNumber = driver.getPhoneNumber();
          this.address = driver.getAddress();
          this.identityCard = driver.getIdentityCard();
          this.sex = driver.getSex();
          this.driverLicense = driver.getDriverLicense();
          this.status = String.valueOf(driver.getStatus()); // Chuyển đổi thành String
          this.avgRating = driver.getAvgRating();
     }
}
