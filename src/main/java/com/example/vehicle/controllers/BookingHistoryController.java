package com.example.vehicle.controllers;

import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.bookinghistory.BookingHistoryResponse;
import com.example.vehicle.services.BookingHistoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingHistoryController {
    BookingHistoryService bookingHistoryService;

    @GetMapping("/booking_history")
    public ResponseEntity<ApiResponse<List<BookingHistoryResponse>>> getBookingHistory() {
        ApiResponse<List<BookingHistoryResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(bookingHistoryService.getAllBookingHistory());
        return ResponseEntity.ok(apiResponse);
    }
}
