package com.example.vehicle.controllers.mocks;

import com.example.vehicle.services.DriverService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MockBookingController {
    DriverService driverService;

    @PostMapping("/{bookingId}/cancel")
    public ResponseEntity<Map<String, Object>> cancel(@PathVariable Long bookingId) {
        Long mockDriverId = 14L; // sau này sẽ lấy từ booking thực
        // Gọi tới service, truyền isFromDriver = false
        driverService.acceptBooking(mockDriverId, "cancel", bookingId);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("bookingId", bookingId);
        response.put("status", "CANCELED");
        response.put("notifyToDriver", mockDriverId);
        response.put("notificationMessage", "Booking " + bookingId + " đã bị huỷ bởi người dùng.");

        return ResponseEntity.ok(response);
    }
}
