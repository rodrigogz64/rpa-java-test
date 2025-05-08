CREATE TABLE cliente (
    id_cliente SERIAL PRIMARY KEY,
    nombre VARCHAR(100),
    email VARCHAR(100),
    estado VARCHAR(20),
    tipo VARCHAR(20),
    saldo NUMERIC(10, 2)
);

CREATE TABLE pedido (
    id_pedido SERIAL PRIMARY KEY,
    id_cliente INTEGER REFERENCES cliente(id_cliente),
    fecha_pedido DATE,
    estado VARCHAR(20),
    total NUMERIC(10, 2)
);

CREATE TABLE producto (
    id_producto SERIAL PRIMARY KEY,
    nombre VARCHAR(50)
);

CREATE TABLE detalle_pedido (
    id_detalle SERIAL PRIMARY KEY,
    id_pedido INTEGER REFERENCES pedido(id_pedido),
    id_producto INTEGER REFERENCES producto(id_producto),
    cantidad INTEGER
);

INSERT INTO cliente (id_cliente, nombre, email, estado, tipo, saldo) VALUES 
(112, 'Ricardo González', 'ricardo.gonzalez@example.com', 'activo', 'Premium', 1840.50),
(113, 'Elena Martínez', 'elena.martinez@example.com', 'inactivo', 'Regular', 762.10),
(114, 'Francisco Ruiz', 'francisco.ruiz@example.com', 'activo', 'Premium', 1020.30),
(115, 'Isabel Morales', 'isabel.morales@example.com', 'activo', 'Regular', 1456.70),
(116, 'Jorge Herrera', 'jorge.herrera@example.com', 'inactivo', 'Premium', 2399.99),
(117, 'Raquel Sánchez', 'raquel.sanchez@example.com', 'activo', 'Regular', 689.40),
(118, 'Martín Álvarez', 'martin.alvarez@example.com', 'activo', 'Premium', 2150.55),
(119, 'Victoria Fernández', 'victoria.fernandez@example.com', 'inactivo', 'Regular', 380.00),
(120, 'Álvaro Pérez', 'alvaro.perez@example.com', 'activo', 'Premium', 1650.80),
(121, 'Carmen López', 'carmen.lopez@example.com', 'activo', 'Regular', 920.10);


INSERT INTO pedido (id_pedido, id_cliente, fecha_pedido, estado, total) VALUES 
(30, 116, '2024-05-01', 'entregado', 523.75),
(31, 116, '2024-05-12', 'pendiente', 780.60),
(32, 116, '2024-05-20', 'entregado', 450.40),
(33, 116, '2024-06-02', 'pendiente', 650.10),
(34, 116, '2024-06-10', 'entregado', 900.30),
(35, 116, '2024-06-15', 'pendiente', 710.50),
(36, 115, '2024-05-03', 'entregado', 345.90),
(37, 115, '2024-05-18', 'pendiente', 920.40),
(38, 115, '2024-06-01', 'entregado', 700.20),
(39, 120, '2024-05-04', 'entregado', 654.75),
(40, 120, '2024-05-17', 'entregado', 475.30),
(41, 120, '2024-06-05', 'pendiente', 510.80),
(42, 120, '2024-06-12', 'entregado', 600.90),
(43, 117, '2024-05-02', 'pendiente', 510.00),
(44, 117, '2024-05-13', 'entregado', 800.00),
(45, 117, '2024-06-08', 'entregado', 650.50),
(46, 117, '2024-06-18', 'pendiente', 530.60),
(47, 112, '2024-05-05', 'entregado', 620.00),
(48, 118, '2024-05-06', 'pendiente', 350.50),
(49, 118, '2024-05-19', 'entregado', 770.40),
(50, 118, '2024-06-04', 'pendiente', 580.20),
(51, 118, '2024-06-14', 'entregado', 615.75),
(52, 113, '2024-05-07', 'entregado', 880.90),
(53, 114, '2024-05-08', 'pendiente', 489.60),
(54, 114, '2024-05-20', 'entregado', 695.50),
(55, 114, '2024-06-06', 'entregado', 712.30),
(56, 114, '2024-06-16', 'pendiente', 475.10),
(57, 119, '2024-05-09', 'entregado', 520.30),
(58, 119, '2024-05-21', 'pendiente', 850.00),
(59, 119, '2024-06-07', 'entregado', 780.80),
(60, 119, '2024-06-17', 'pendiente', 600.00),
(61, 121, '2024-05-10', 'entregado', 603.00),
(62, 121, '2024-05-22', 'pendiente', 460.50),
(63, 121, '2024-06-09', 'entregado', 730.00),
(64, 121, '2024-06-19', 'pendiente', 480.10);

INSERT INTO producto (id_producto, nombre) VALUES 
(201, 'Laptop HP Pavilion'),
(202, 'Smartphone Samsung Galaxy'),
(203, 'Tablet iPad Pro'),
(204, 'Monitor LG UltraWide'),
(205, 'Impresora Epson EcoTank'),
(206, 'Teclado Mecánico Logitech'),
(207, 'Mouse Inalámbrico Microsoft'),
(208, 'Auriculares Sony WH-1000XM4'),
(209, 'Cámara Canon EOS'),
(210, 'Disco Duro Externo Seagate');

INSERT INTO detalle_pedido (id_detalle, id_pedido, id_producto, cantidad) VALUES 
(101, 30, 201, 1),
(102, 30, 206, 2),
(103, 31, 202, 1),
(104, 31, 207, 1),
(105, 32, 208, 1),
(106, 33, 203, 1),
(107, 34, 204, 2),
(108, 35, 205, 1),
(109, 36, 209, 1),
(110, 37, 201, 1),
(111, 37, 210, 2),
(112, 38, 202, 1),
(113, 39, 203, 1),
(114, 40, 204, 1),
(115, 41, 205, 1),
(116, 42, 206, 2),
(117, 43, 207, 1),
(118, 44, 208, 1),
(119, 45, 209, 1),
(120, 46, 210, 2),
(121, 47, 201, 1),
(122, 48, 202, 1),
(123, 49, 203, 1),
(124, 50, 204, 1),
(125, 51, 205, 1),
(126, 52, 206, 2),
(127, 53, 207, 1),
(128, 54, 208, 1),
(129, 55, 209, 1),
(130, 56, 210, 1),
(131, 57, 201, 1),
(132, 58, 202, 1),
(133, 59, 203, 1),
(134, 60, 204, 1),
(135, 61, 205, 1),
(136, 62, 206, 1),
(137, 63, 207, 2),
(138, 64, 208, 1);