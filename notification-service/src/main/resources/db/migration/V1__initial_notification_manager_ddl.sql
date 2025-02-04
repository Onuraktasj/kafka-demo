CREATE TABLE notifications (
                               id BIGSERIAL PRIMARY KEY,
                               shipment_id BIGINT NOT NULL,
                               recipient_email VARCHAR(255) NOT NULL,
                               message TEXT NOT NULL,
                               type VARCHAR(50) NOT NULL,
                               status VARCHAR(50) NOT NULL,
                               created_at TIMESTAMP NOT NULL,
                               updated_at TIMESTAMP
);
