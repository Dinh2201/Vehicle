CREATE TABLE driver_vehicle_history (
    history_id BIGINT PRIMARY KEY,  -- Sử dụng BIGINT cho ID vì có thể có số lượng lớn bản ghi
    vehicle_id BIGINT NOT NULL,     -- Khoá ngoại tham chiếu đến bảng vehicle
    driver_id BIGINT NOT NULL,      -- Khoá ngoại tham chiếu đến bảng driver
    start_date TIMESTAMP NOT NULL,  -- Ngày bắt đầu
    end_date TIMESTAMP,             -- Ngày kết thúc (có thể null)

    CONSTRAINT fk_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id) ON DELETE CASCADE,
    CONSTRAINT fk_driver FOREIGN KEY (driver_id) REFERENCES driver(driver_id) ON DELETE CASCADE
);

CREATE SEQUENCE driver_vehicle_history_history_id_seq START WITH 1 INCREMENT BY 1;



