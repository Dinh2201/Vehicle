package com.example.vehicle.controllers;

import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.bookinghistory.BookingHistoryResponse;
import com.example.vehicle.services.BookingHistoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingHistoryController {
    BookingHistoryService bookingHistoryService;

    @GetMapping("/booking_history")
    public ResponseEntity<ApiResponse<List<BookingHistoryResponse>>> getBookingHistory(
            @RequestParam (required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false, defaultValue = "5") int pageSize,
            @RequestParam(required = false, defaultValue ="bookingId" ) String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") String sortDir
            ) {
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("ASC")){
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }
        ApiResponse<List<BookingHistoryResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(bookingHistoryService.getAllBookingHistory(PageRequest.of(pageNo-1,pageSize, sort)));
        return ResponseEntity.ok(apiResponse);
    }
}
