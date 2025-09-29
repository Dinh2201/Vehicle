package com.example.vehicle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION("D099"),
    VEHICLE_LICENSE_PLATE_EXCEPTION("D001"),
    VEHICLE_TYPE_EXCEPTION("D002"),
    DRIVER_EXCEPTION("D003"),
    VEHICLE_EXCEPTION("D004"),
    HISTORY_NOT_FOUND("D005"),
    UNKNOWN_ERROR("D006"),
    DRIVERS_NOT_FOUND("D007"),
    VEHICLES_NOT_FOUND("D008"),
    VEHICLE_TYPES_NOT_FOUND("D009"),
    VALIDATION("4000");

    private final String code;
}
