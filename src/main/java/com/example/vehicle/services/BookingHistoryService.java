package com.example.vehicle.services;

import com.example.vehicle.dtos.response.DriverVehicleHistoryResponse;
import com.example.vehicle.dtos.response.bookinghistory.BookingHistoryResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookingHistoryService {

    List<BookingHistoryResponse> getAllBookingHistory(Pageable pageable);
}
