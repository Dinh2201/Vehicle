package com.example.vehicle.services;

import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.bookinghistory.BookingHistoryResponse;

import java.util.List;

public interface BookingHistoryService {

    ApiResponse<List<BookingHistoryResponse>> getAllBookingHistory(int pageNo, int pageSize, String sortBy, String sortDir);
}
