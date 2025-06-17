CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
);

INSERT INTO users (id, email, username, password, role)
VALUES (1, 'testuser@example.com', 'testuser', '$2a$10$UeXqSIlRzv1ChZaKsnpoFe5V6Oz35PxVzRqqPih9L.xHf5JqVr9MC', 'ROLE_USER');
