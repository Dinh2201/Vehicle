package com.example.vehicle.exceptions;


public enum ErrorCode {
    UNCATEGORIED_EXCEPTION(9999, "Uncategoried error"),
    VEHICLE_LICENSE_PLATE_EXCEPTION(1001,"Vehicle license plate existed"),
    VEHICLE_TYPE_EXCEPTION(1002,"Vehicle type not found"),
    DRIVER_EXCEPTION(1003,"Driver not found"),
    VEHICLE_EXCEPTION(1004,"Vehicle not found"),
    HISTORY_NOT_FOUND(1005,"No history records found for this driver"),
    VALIDATION(4000,"Validation error"),
    ;
    private int code;
    private String message;

    ErrorCode( int code, String message) {
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
