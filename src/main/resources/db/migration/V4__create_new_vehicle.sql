INSERT INTO vehicle (vehicle_id,
                     vehicle_name,
                     license_plate,
                     status,
                     signup_date,
                     vehicle_type_id,
                     driver_id)
VALUES (nextval('vehicle_vehicle_id_seq'),
        'xe ab',
        'abc-12895',
        'ACTIVE',
        '2025-09-09',
        2,
        1);

