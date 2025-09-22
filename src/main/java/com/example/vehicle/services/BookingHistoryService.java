package com.example.vehicle.services;

import com.example.vehicle.dtos.response.DriverVehicleHistoryResponse;
import com.example.vehicle.dtos.response.bookinghistory.BookingHistoryResponse;

import java.util.List;

public interface BookingHistoryService {

    List<BookingHistoryResponse> getAllBookingHistory();
}
