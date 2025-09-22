package com.example.vehicle.exceptions;

import com.example.vehicle.configs.Translator;



public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, Translator.toLocale("UNCATEGORIZED_EXCEPTION")),
    VEHICLE_LICENSE_PLATE_EXCEPTION(1001, Translator.toLocale("VEHICLE_LICENSE_PLATE_EXCEPTION")),
    VEHICLE_TYPE_EXCEPTION(1002,Translator.toLocale("VEHICLE_TYPE_EXCEPTION")),
    DRIVER_EXCEPTION(1003,Translator.toLocale("DRIVER_EXCEPTION")),
    VEHICLE_EXCEPTION(1004,Translator.toLocale("VEHICLE_EXCEPTION")),
    HISTORY_NOT_FOUND(1005,Translator.toLocale("HISTORY_NOT_FOUND")),
    VALIDATION(4000,Translator.toLocale("VALIDATION")),
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
