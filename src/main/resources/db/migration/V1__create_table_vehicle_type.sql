CREATE TABLE vehicle_type (
    vehicle_type_id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- -- Tạo Sequence cho VehicleType
CREATE SEQUENCE vehicle_type_vehicle_type_id_seq START WITH 1 INCREMENT BY 1;