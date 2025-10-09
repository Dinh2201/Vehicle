package com.example.vehicle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION("E999"),
    VEHICLE_LICENSE_PLATE_EXCEPTION("E001"),
    VEHICLE_TYPE_EXCEPTION("E002"),
    DRIVER_EXCEPTION("E003"),
    VEHICLE_EXCEPTION("E004"),
    HISTORY_NOT_FOUND("E005"),
    UNKNOWN_ERROR("E006"),
    DRIVERS_NOT_FOUND("E007"),
    VEHICLES_NOT_FOUND("E008"),
    VEHICLE_TYPES_NOT_FOUND("E009"),
    ERROR_SAVING_BOOKING_HISTORY("E010"),
    VALIDATION("4000");

    private final String code;
}
