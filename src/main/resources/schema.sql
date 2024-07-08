CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE courses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

INSERT INTO users (name, email, password) VALUES
('John', 'john.doe@example.com', 'password1'),
('Jane', 'jane.smith@example.com', 'password2'),
('Alice', 'alice.johnson@example.com', 'password3'),
('Bob', 'bob.brown@example.com', 'password4'),
('Charlie', 'charlie.davis@example.com', 'password5');

INSERT INTO courses (name, description) VALUES
('Java Basics', 'Introduction to Java programming.'),
('Advanced Java', 'Deep dive into Java topics.'),
('Spring Boot', 'Learn how to build applications with Spring Boot.'),
('Data Structures', 'Understanding data structures and algorithms.'),
('Web Development', 'Building web applications with Java.');
