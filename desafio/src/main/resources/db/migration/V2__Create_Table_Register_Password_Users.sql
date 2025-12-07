CREATE TABLE register_password_users (
    id SERIAL PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    id_user BIGINT REFERENCES "users"(id) ON DELETE CASCADE,
    expires_at TIMESTAMP,
    used BOOLEAN DEFAULT FALSE
);
