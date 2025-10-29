package com.example.vehicle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    VEHICLE_CREATE("S001"),
    VEHICLE_GET_ALL("S002"),
    VEHICLE_GET_BY_ID("S003"),
    VEHICLE_UPDATE("S004"),
    VEHICLE_DELETE("S005"),
    DRIVER_DELETED("S006"),
    DRIVER_CREATE("S007"),
    DRIVER_GET_ALL("S008"),
    DRIVER_GET_BY_ID("S009"),
    DRIVER_UPDATE("S010"),
    DRIVER_ACCEPT("S011"),
    DRIVER_REJECT("S012"),
    USER_CANCEL("S013"),
    DRIVER_NOTIFY("S014"),
    VEHICLE_TYPE_CREATE("S015"),
    VEHICLE_TYPE_GET_ALL("S016"),
    VEHICLE_TYPE_GET_BY_ID("S017"),
    VEHICLE_TYPE_UPDATE("S018"),
    VEHICLE_TYPE_DELETE("S019"),
    DRIVER_VEHICLE_HISTORY("S020"),
    BOOKING_HISTORY("S021"),
    DRIVER_CANCEL("S022");


    private final String code;
}
