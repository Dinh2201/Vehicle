-- 1. Tạo sequence nếu chưa có
CREATE SEQUENCE IF NOT EXISTS booking_history_id_seq
    START WITH 1
    INCREMENT BY 1;

-- 2. Thêm cột id vào bảng nếu chưa có
ALTER TABLE booking_history
    ADD COLUMN IF NOT EXISTS id BIGINT;

-- 3. Gán sequence làm mặc định cho id
ALTER TABLE booking_history
    ALTER COLUMN id SET DEFAULT nextval('booking_history_id_seq');

-- 4. Cập nhật giá trị id cho bản ghi cũ (nếu NULL)
UPDATE booking_history
SET id = nextval('booking_history_id_seq')
WHERE id IS NULL;

-- 5. Gỡ khóa chính cũ (trên booking_id)
ALTER TABLE booking_history
DROP CONSTRAINT IF EXISTS booking_history_pkey;

-- 6. Đặt id làm khóa chính mới
ALTER TABLE booking_history
    ADD CONSTRAINT pk_booking_history PRIMARY KEY (id);
