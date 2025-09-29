package com.example.vehicle.exceptions;

import com.example.vehicle.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppException extends RuntimeException {

    private final ErrorCode errorCode;
}
