
-- Tạo bảng Vehicle
CREATE TABLE vehicle (
        vehicle_id BIGINT PRIMARY KEY,
        vehicle_name VARCHAR(255) NOT NULL,
        license_plate VARCHAR(50) UNIQUE NOT NULL,
        status VARCHAR(50),
        signup_date TIMESTAMP,
        latitude DOUBLE PRECISION,
        longitude DOUBLE PRECISION,
        vehicle_type_id BIGINT,
        CONSTRAINT fk_vehicle_type FOREIGN KEY (vehicle_type_id) REFERENCES vehicle_type(vehicle_type_id)
);



-- -- Tạo Sequence cho Vehicle
CREATE SEQUENCE vehicle_vehicle_id_seq START WITH 1 INCREMENT BY 1;


