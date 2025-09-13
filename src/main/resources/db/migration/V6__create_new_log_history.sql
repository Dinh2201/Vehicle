
-- Chèn thông tin vào bảng driver_vehicle_history
INSERT INTO driver_vehicle_history (history_id, vehicle_id, driver_id, start_date)
VALUES ( nextval('driver_vehicle_history_history_id_seq'),
        1, 2, CURRENT_TIMESTAMP);
