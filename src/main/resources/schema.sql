CREATE TABLE department (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
);

-- Создание таблицы worker
CREATE TABLE IF NOT EXISTS worker (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    last_name VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Примеры данных для тестирования
INSERT INTO worker (last_name, position, role, login, password) VALUES
    ('Иванов', 'Инженер', 'USER', 'ivanov_user', '{noop}password123'),
    ('Петров', 'Техник', 'ADMIN', 'petrov_admin', '{noop}admin456'),
    ('Сидоров', 'Оператор', 'VIEWER', 'sidorov_viewer', '{noop}viewer789');

CREATE TABLE IF NOT EXISTS spare_part (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    part_name VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS radio_type (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    type_name VARCHAR(255) NOT NULL,
    description TEXT
);

