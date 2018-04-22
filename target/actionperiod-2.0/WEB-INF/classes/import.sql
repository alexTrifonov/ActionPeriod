CREATE TABLE IF NOT EXISTS roles (id SERIAL PRIMARY KEY, name VARCHAR(100));

CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, login VARCHAR(100), password VARCHAR(100), role_id INTEGER REFERENCES roles(id), at_work BOOLEAN, work_start BIGINT, work_full BIGINT, enabled BOOLEAN);

INSERT INTO roles(name) VALUES ('ROLE_USER');

--Insert user with login = "user" and password = "password", user with login = "Tom" and password = "Password"
INSERT INTO users(login, password, role_id, at_work, work_start, work_full, enabled) VALUES ('user', '$2a$10$RikA1171REMRwnrFvpkSteWfwAo/bUVj7AC1cZ7WG7EM6gTFgmm56', 1, 'false', 0, 0, 'true');
INSERT INTO users(login, password, role_id, at_work, work_start, work_full, enabled) VALUES ('Tom', '$2a$10$RikA1171REMRwnrFvpkSteWfwAo/bUVj7AC1cZ7WG7EM6gTFgmm56', 1, 'false', 0, 0, 'true');