CREATE TABLE outbox_messages (
                                id BIGSERIAL PRIMARY KEY,
                                topic VARCHAR(255) NOT NULL,
                                message VARCHAR NOT NULL,
                                processed BOOLEAN DEFAULT FALSE
);