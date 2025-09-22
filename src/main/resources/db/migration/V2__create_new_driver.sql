INSERT INTO driver (driver_id, name, age, phone_number, address, identity_card, sex, driver_license, status, avg_rating)
VALUES (nextval('driver_driver_id_seq'), 'Pham Thi M', '26', '0911223344', '60 Hai Ba Trung, District 3, Ho Chi Minh City', '246813579', 'Nữ', 'A246813579', 'ACTIVE', 4.60),
(nextval('driver_driver_id_seq'), 'Nguyen Thi L', '30', '0911334455', '123 Le Lai, District 1, Ho Chi Minh City', '123456789', 'Nữ', 'B123456789', 'ACTIVE', 4.75),
  (nextval('driver_driver_id_seq'), 'Tran Minh T', '28', '0911445566', '45 Pham Ngoc Thach, District 3, Ho Chi Minh City', '987654321', 'Nam', 'C987654321', 'INACTIVE', 4.20),
  (nextval('driver_driver_id_seq'), 'Le Anh D', '35', '0911556677', '32 Nguyen Du, District 1, Ho Chi Minh City', '246823579', 'Nam', 'D246823579', 'ACTIVE', 4.80),
  (nextval('driver_driver_id_seq'), 'Hoang Thi B', '24', '0911667788', '22 Le Hong Phong, District 5, Ho Chi Minh City', '654321987', 'Nữ', 'E654321987', 'INACTIVE', 4.50);