-- Inserting data

INSERT INTO employee (first_name, last_name, email)
VALUES
    ('Leslie', 'Andrews', 'leslie@luv2code.com'),
    ('Emma', 'Baumgarten', 'emma@luv2code.com'),
    ('Yuri', 'Petrov', 'yuri@luv2code.com'),
    ('Juan', 'Vega', 'juan@luv2code.com');

INSERT INTO roles (user_id, role)
VALUES
    ('john', 'ROLE_EMPLOYEE'),
    ('mary', 'ROLE_EMPLOYEE'),
    ('mary', 'ROLE_MANAGER'),
    ('susan', 'ROLE_EMPLOYEE'),
    ('susan', 'ROLE_MANAGER'),
    ('susan', 'ROLE_ADMIN');

INSERT INTO system_users (user_id, password, active)
VALUES
    ('john', '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', TRUE),
    ('mary', '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', TRUE),
    ('susan', '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', TRUE);
