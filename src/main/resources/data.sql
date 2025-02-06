CREATE TABLE users (
                       ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       gender VARCHAR(10) NOT NULL,
                       photo VARCHAR(255),
                       country VARCHAR(50) NOT NULL,
                       state VARCHAR(50) NOT NULL,
                       city VARCHAR(50) NOT NULL
);

INSERT INTO users (username, name, email, gender, photo, country, state, city) VALUES
                                                                                   ('jgarcia', 'Juan García', 'juan.garcia@example.com', 'MALE', 'https://example.com/photo1.jpg', 'España', 'Madrid', 'Madrid'),
                                                                                   ('mlopez', 'María López', 'maria.lopez@example.com', 'FEMALE', 'https://example.com/photo2.jpg', 'España', 'Cataluña', 'Barcelona'),
                                                                                   ('rcastro', 'Roberto Castro', 'roberto.castro@example.com', 'MALE', 'https://example.com/photo3.jpg', 'Venezuela', 'Miranda', 'Caracas'),
                                                                                   ('afernandez', 'Ana Fernández', 'ana.fernandez@example.com', 'FEMALE', 'https://example.com/photo4.jpg', 'Venezuela', 'Zulia', 'Maracaibo'),
                                                                                   ('dfuentes', 'Daniel Fuentes', 'daniel.fuentes@example.com', 'MALE', 'https://example.com/photo5.jpg', 'Colombia', 'Antioquia', 'Medellín'),
                                                                                   ('ccardenas', 'Carla Cárdenas', 'carla.cardenas@example.com', 'FEMALE', 'https://example.com/photo6.jpg', 'Colombia', 'Bogotá', 'Bogotá'),
                                                                                   ('pmartinez', 'Pablo Martínez', 'pablo.martinez@example.com', 'MALE', 'https://example.com/photo7.jpg', 'España', 'Andalucía', 'Sevilla'),
                                                                                   ('vrodriguez', 'Valeria Rodríguez', 'valeria.rodriguez@example.com', 'FEMALE', 'https://example.com/photo8.jpg', 'Venezuela', 'Carabobo', 'Valencia'),
                                                                                   ('lgomez', 'Luis Gómez', 'luis.gomez@example.com', 'MALE', 'https://example.com/photo9.jpg', 'Colombia', 'Valle del Cauca', 'Cali'),
                                                                                   ('esanchez', 'Elena Sánchez', 'elena.sanchez@example.com', 'FEMALE', 'https://example.com/photo10.jpg', 'España', 'Galicia', 'Santiago de Compostela');
