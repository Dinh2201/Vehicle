package com.example.vehicle.services.impls;

import com.example.vehicle.dtos.response.bookinghistory.BookingHistoryResponse;
import com.example.vehicle.entities.BookingHistory;
import com.example.vehicle.mappers.BookingHistoryMapper;
import com.example.vehicle.repositories.BookingHistoryRepository;
import com.example.vehicle.services.BookingHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingHistoryImpl implements BookingHistoryService {

    private final BookingHistoryRepository bookingHistoryRepository;
    private final BookingHistoryMapper bookingHistoryMapper;

    @Override
    public List<BookingHistoryResponse> getAllBookingHistory(Pageable pageable) {
        List<BookingHistory> bookingHistoryList = bookingHistoryRepository.findAll(pageable).getContent();

        List<BookingHistoryResponse> responses = bookingHistoryMapper.toBookingHistoryResponses(bookingHistoryList);
        log.info("Booking history get all list");
        return responses;
    }
}
