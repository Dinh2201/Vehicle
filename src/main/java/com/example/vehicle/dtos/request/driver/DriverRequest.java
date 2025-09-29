package com.example.vehicle.dtos.request.driver;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverRequest {
     @NotBlank(message = "{name.driver.notnull}")
     String name;

     @NotBlank(message = "{age.driver.notnull}")
     String age;

     @NotBlank(message = "{phone.number.driver.notnull}")
     String phoneNumber;

     @NotBlank(message = "{address.driver.notnull}")
     String address;

     @NotBlank(message = "{identity.card.notnull}")
     String identityCard;

     @NotBlank(message = "{sex.driver.notnull}")
     String sex;

     @NotBlank(message = "{driver.license.notnull}")
     String driverLicense;

     String status;

     BigDecimal avgRating;
}
