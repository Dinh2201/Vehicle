package com.example.vehicle.mappers;

import com.example.vehicle.dtos.response.bookinghistory.BookingHistoryResponse;
import com.example.vehicle.entities.BookingHistory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingHistoryMapper {
    BookingHistoryResponse toBookingHistoryResponse(BookingHistory bookingHistory);

    List<BookingHistoryResponse> toBookingHistoryResponses(List<BookingHistory> bookingHistories);
}
