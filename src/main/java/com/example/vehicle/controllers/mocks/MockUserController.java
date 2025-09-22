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
public class MockUserController {

    DriverNotificationService driverNotificationService;

    @PostMapping("/{bookingId}/accept")
    public ResponseEntity<Map<String, Object>> accept(@PathVariable Long bookingId) {
        Long mockDriverId = 2L; // sau này sẽ lấy từ booking thực
        driverNotificationService.notifyDriver(mockDriverId, "Booking " + bookingId + " đã được chấp nhận bởi người dùng.");
        return ResponseEntity.ok(Map.of(
                "bookingId", bookingId,
                "status", "ACCEPTED",
                "message", "Booking đã được chấp nhận."
        ));
    }

    @PostMapping("/{bookingId}/reject")
    public ResponseEntity<Map<String, Object>> reject(@PathVariable Long bookingId) {
        Long mockDriverId = 2L; // sau này sẽ lấy từ booking thực
        driverNotificationService.notifyDriver(mockDriverId, "Booking " + bookingId + " đã bị từ chối bởi người dùng.");
        return ResponseEntity.ok(Map.of(
                "bookingId", bookingId,
                "status", "REJECTED",
                "message", "Booking đã bị từ chối."
        ));
    }

    @PostMapping("/{bookingId}/cancel")
    public ResponseEntity<Map<String, Object>> cancel(@PathVariable Long bookingId) {
        Long mockDriverId = 2L; // sau này sẽ lấy từ booking thực
        driverNotificationService.notifyDriver(mockDriverId, "Booking " + bookingId + " đã bị hủy bởi người dùng.");
        return ResponseEntity.ok(Map.of(
                "bookingId", bookingId,
                "status", "CANCELED",
                "message", "Booking đã bị hủy bởi người dùng."
        ));
    }
}
