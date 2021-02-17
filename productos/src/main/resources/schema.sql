DROP TABLE producto IF EXISTS;

CREATE TABLE producto  (
    producto_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    marca VARCHAR(20) NOT NULL,
    precio BIGINT NOT NULL,
    stock BIGINT NOT NULL,
    estado VARCHAR(20) NOT NULL,
    descuento BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS carrito  (
    carrito_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    fecha DATE NOT NULL,
    facturado TINYINT    
);

CREATE TABLE IF NOT EXISTS carrito_detalle (
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    carrito_id BIGINT ,
    producto_id BIGINT,
    cantidad BIGINT 
);

