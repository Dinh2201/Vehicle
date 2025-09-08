-- Tạo bảng Driver
CREATE TABLE driver (
    driver_id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age VARCHAR(10),
    phone_number VARCHAR(50),
    address TEXT,
    identity_card VARCHAR(50),
    sex VARCHAR(10),
    driver_license VARCHAR(50),
    status VARCHAR(50), -- Enum sẽ được lưu dưới dạng string
    avg_rating DECIMAL(5, 2)
);

-- -- Tạo Sequence cho Driver
CREATE SEQUENCE driver_driver_id_seq START WITH 1 INCREMENT BY 1;