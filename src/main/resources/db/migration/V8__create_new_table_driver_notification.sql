CREATE TABLE IF NOT EXISTS driver_notification (
    noty_id BIGSERIAL PRIMARY KEY,
    driver_id BIGINT NOT NULL,
    message VARCHAR(50) ,
    created_at TIMESTAMP ,

    CONSTRAINT fk_driver_dn FOREIGN KEY (driver_id) REFERENCES driver(driver_id) ON DELETE CASCADE
);

