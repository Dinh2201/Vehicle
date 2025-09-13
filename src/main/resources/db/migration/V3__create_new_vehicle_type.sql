INSERT INTO vehicle_type (vehicle_type_id, name, description )
VALUES (nextval('vehicle_type_vehicle_type_id_seq'),
        'xe số',
        'là loại xe máy có động cơ và hộp số được điều khiển thủ công, yêu cầu người lái phải sử dụng cần số để thao tác sang số (tăng/giảm cấp số) tùy theo tốc độ và điều kiện vận hành'),
    (nextval('vehicle_type_vehicle_type_id_seq'), 'xe ga', 'là loại xe máy có động cơ và hộp số tự động, người lái không cần phải thao tác cần số, chỉ cần điều khiển tay ga để điều chỉnh tốc độ.'),
    (nextval('vehicle_type_vehicle_type_id_seq'), 'xe tay ga điện', 'là loại xe tay ga sử dụng động cơ điện thay vì động cơ đốt trong, được điều khiển bởi pin sạc, không cần nhiên liệu hóa thạch.'),
    (nextval('vehicle_type_vehicle_type_id_seq'), 'xe tải', 'là loại xe có tải trọng lớn, được thiết kế để vận chuyển hàng hóa, có thể sử dụng cho các mục đích công nghiệp hoặc vận chuyển thương mại.'),
    (nextval('vehicle_type_vehicle_type_id_seq'), 'xe bus', 'là loại xe được thiết kế để vận chuyển số lượng lớn hành khách trên các tuyến đường cố định, thường phục vụ các hệ thống giao thông công cộng.');