CREATE TABLE projects (
    id BIGSERIAL PRIMARY KEY,
    project_creator VARCHAR(255) NOT NULL,
    password_access VARCHAR(255) NOT NULL,
    name_project VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATE,
    end_date DATE
);
