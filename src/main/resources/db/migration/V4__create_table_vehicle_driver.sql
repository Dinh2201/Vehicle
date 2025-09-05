-- Tạo bảng Vehicle_Drivers (Many-to-Many)
CREATE TABLE vehicle_drivers (
        vehicle_id BIGINT,
        driver_id BIGINT,
        PRIMARY KEY (vehicle_id, driver_id),
        CONSTRAINT fk_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id),
        CONSTRAINT fk_driver FOREIGN KEY (driver_id) REFERENCES driver(driver_id)
);