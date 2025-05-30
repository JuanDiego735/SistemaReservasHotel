
CREATE DATABASE IF NOT EXISTS SistemaReservasHotel;
USE SistemaReservasHotel;

CREATE TABLE Cliente (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    dni VARCHAR(20) NOT NULL,
    correo VARCHAR(50),
    telefono VARCHAR(20)
);

CREATE TABLE Habitacion (
    idHabitacion INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    disponible BOOLEAN NOT NULL
);

CREATE TABLE Reserva (
    idReserva INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT NOT NULL,
    idHabitacion INT NOT NULL,
    fechaEntrada DATE NOT NULL,
    fechaSalida DATE NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente),
    FOREIGN KEY (idHabitacion) REFERENCES Habitacion(idHabitacion)
);

CREATE TABLE Factura (
    idFactura INT AUTO_INCREMENT PRIMARY KEY,
    idReserva INT NOT NULL,
    montoTotal DECIMAL(10, 2) NOT NULL,
    fechaEmision DATE NOT NULL,
    FOREIGN KEY (idReserva) REFERENCES Reserva(idReserva)
);
