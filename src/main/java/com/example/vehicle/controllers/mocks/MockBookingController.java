package com.example.vehicle.controllers.mocks;

import com.example.vehicle.services.DriverNotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MockBookingController {

    DriverNotificationService driverNotificationService;

    @PostMapping("/{bookingId}/cancel")
    public ResponseEntity<Map<String, Object>> cancel(@PathVariable Long bookingId) {
        Long mockDriverId = 1L; // sau này sẽ lấy từ booking thực
        driverNotificationService.notifyDriver(mockDriverId, "Booking " + bookingId + " đã bị hủy bởi người dùng.");
        return ResponseEntity.ok(Map.of(
                "bookingId", bookingId,
                "status", "CANCELED",
                "message", "Booking đã bị hủy bởi người dùng."
        ));
    }
}
