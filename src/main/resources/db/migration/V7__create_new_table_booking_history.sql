CREATE TABLE booking_history (
        booking_id BIGINT PRIMARY KEY,
        vehicle_id BIGINT NOT NULL,
        driver_id BIGINT NOT NULL,
        booking_status VARCHAR(50) ,
        updated_at TIMESTAMP ,

        CONSTRAINT fk_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id) ON DELETE CASCADE,
        CONSTRAINT fk_driver FOREIGN KEY (driver_id) REFERENCES driver(driver_id) ON DELETE CASCADE
);

CREATE SEQUENCE booking_history_booking_id_seq START WITH 1 INCREMENT BY 1;