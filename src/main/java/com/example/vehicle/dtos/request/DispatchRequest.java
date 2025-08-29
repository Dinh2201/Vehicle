package com.example.vehicle.dtos.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DispatchRequest {
    Double startLatitude;     // tọa độ điểm xuất phát (vĩ độ)
    Double startLongitude;
    Boolean isAccept;
}
