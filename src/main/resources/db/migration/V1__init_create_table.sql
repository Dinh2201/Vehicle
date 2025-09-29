CREATE TABLE IF NOT EXISTS vehicle_type (
    vehicle_type_id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- -- Tạo Sequence cho VehicleType
CREATE SEQUENCE IF NOT EXISTS vehicle_type_vehicle_type_id_seq START WITH 1 INCREMENT BY 1;

-- Tạo bảng Driver
CREATE TABLE IF NOT EXISTS driver (
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
CREATE SEQUENCE IF NOT EXISTS driver_driver_id_seq START WITH 1 INCREMENT BY 1;

-- Tạo bảng Vehicle
CREATE TABLE IF NOT EXISTS vehicle (
    vehicle_id BIGINT PRIMARY KEY,
    vehicle_name VARCHAR(255) NOT NULL,
    license_plate VARCHAR(50) UNIQUE NOT NULL,
    status VARCHAR(50),
    signup_date TIMESTAMP,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    vehicle_type_id BIGINT,
    driver_id BIGINT,
    CONSTRAINT fk_vehicle_type FOREIGN KEY (vehicle_type_id) REFERENCES vehicle_type(vehicle_type_id),
    CONSTRAINT fk_driver FOREIGN KEY (driver_id) REFERENCES driver(driver_id)
);


-- -- Tạo Sequence cho Vehicle
CREATE SEQUENCE IF NOT EXISTS vehicle_vehicle_id_seq START WITH 1 INCREMENT BY 1;


