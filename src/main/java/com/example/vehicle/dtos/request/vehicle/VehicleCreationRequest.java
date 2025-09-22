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

    @NotBlank(message = "Trạng thái không được để trống")
     String status;

    @NotNull(message = "Ngày đăng ký không được để trống")
    LocalDate signupDate;

    Double latitude;

    Double longitude;

    @NotNull(message = "ID loại xe là bắt buộc")
     long vehicleType; // chứa ID

    @NotNull(message = "ID tài xế không được rỗng")
     long driver;
}
