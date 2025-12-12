  CREATE TABLE register_password_projects(
  id BIGSERIAL PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    id_project BIGINT NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    used BOOLEAN NOT NULL DEFAULT FALSE
    );