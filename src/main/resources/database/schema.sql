-- Drop table if exists.
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

-- Create employee table
CREATE TABLE employees
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(45),
    last_name  VARCHAR(45),
    email      VARCHAR(45)
);

CREATE TABLE system_users
(
    user_id  VARCHAR(50) NOT NULL,
    password CHAR(68)    NOT NULL,
    active   BOOLEAN     NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE roles
(
    user_id VARCHAR(50) NOT NULL,
    role    VARCHAR(50) NOT NULL,
    UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES system_users (user_id)
);

