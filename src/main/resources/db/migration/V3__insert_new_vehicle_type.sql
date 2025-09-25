INSERT INTO vehicle_type (vehicle_type_id, name, description )
VALUES (nextval('vehicle_type_vehicle_type_id_seq'),
        'xe số',
        'là loại xe máy có động cơ và hộp số được điều khiển thủ công, yêu cầu người lái phải sử dụng cần số để thao tác sang số (tăng/giảm cấp số) tùy theo tốc độ và điều kiện vận hành'),
    (nextval('vehicle_type_vehicle_type_id_seq'), 'xe ga', 'là loại xe máy có động cơ và hộp số tự động, người lái không cần phải thao tác cần số, chỉ cần điều khiển tay ga để điều chỉnh tốc độ.'),
       (nextval('vehicle_type_vehicle_type_id_seq'),
        'xe hơi',
        'là loại ô tô được thiết kế để chở người, thường có 4 bánh, động cơ mạnh hơn xe máy, phù hợp cho di chuyển đường dài và đảm bảo sự an toàn, tiện nghi.');
