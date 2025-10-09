package com.example.vehicle.services.impls;

import com.example.vehicle.dtos.response.ApiResponse;
import com.example.vehicle.dtos.response.bookinghistory.BookingHistoryResponse;
import com.example.vehicle.entities.BookingHistory;
import com.example.vehicle.enums.SuccessCode;
import com.example.vehicle.mappers.BookingHistoryMapper;
import com.example.vehicle.repositories.BookingHistoryRepository;
import com.example.vehicle.services.BookingHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingHistoryImpl implements BookingHistoryService {

    private final BookingHistoryRepository bookingHistoryRepository;
    private final BookingHistoryMapper bookingHistoryMapper;

    @Override
    public ApiResponse<List<BookingHistoryResponse>> getAllBookingHistory(int pageNo, int pageSize, String sortBy, String sortDir) {
        log.info("Paging");
        Sort sort = sortDir.equalsIgnoreCase("ASC")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        List<BookingHistory> bookingHistoryList = bookingHistoryRepository.findAll(pageable).getContent();

        List<BookingHistoryResponse> responses = bookingHistoryMapper.toBookingHistoryResponses(bookingHistoryList);

        ApiResponse<List<BookingHistoryResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(SuccessCode.BOOKING_HISTORY.getCode());
        apiResponse.setData(responses);
        log.info("Booking history get all list");
        return apiResponse;
    }
}
