-- Insert into USUARIO (two users: an admin and a client)
INSERT INTO USUARIO (username, nombre, telefono, correo, direccion, password)
VALUES 
    ('admin1', 'Admin User', '+1234567890', 'admin@example.com', '123 Admin St', 'hashed_password1'),
    ('client1', 'Client User', '+0987654321', 'client@example.com', '456 Client Ave', 'hashed_password2');

-- Insert into ADMIN (for admin1)
INSERT INTO ADMIN (usuario_id, fecha_ingreso)
VALUES (1, '2023-01-01');

-- Insert into CLIENTE (for client1)
INSERT INTO CLIENTE (usuario_id, direccion_envio)
VALUES (2, '456 Client Ave');

-- Paso 1: Insertar Electronics
INSERT INTO CATEGORIA (nombre, padre_id) VALUES ('Electronics', NULL);
-- Obtener su ID
SET @id_electronics = LAST_INSERT_ID();

-- Paso 2: Insertar Computers
INSERT INTO CATEGORIA (nombre, padre_id) VALUES ('Computers', @id_electronics);
SET @id_computers = LAST_INSERT_ID();

-- Paso 3: Insertar Laptops
INSERT INTO CATEGORIA (nombre, padre_id) VALUES ('Laptops', @id_computers);

-- Paso 4: Insertar Smartphones
INSERT INTO CATEGORIA (nombre, padre_id) VALUES ('Smartphones', @id_electronics);


-- Insert into MARCA (brands)
INSERT INTO MARCA (nombre)
VALUES 
    ('Apple'),
    ('Samsung'),
    ('Dell');

-- Insert into PRODUCTO (products)
INSERT INTO PRODUCTO (nombre, descripcion, sku, precio_venta, categoria_id, marca_id)
VALUES 
    ('MacBook Pro', 'Laptop Apple MacBook Pro', 'MBP123', 1999.99, 3, 1),
    ('Galaxy S21', 'Smartphone Samsung Galaxy S21', 'GS21123', 799.99, 4, 2),
    ('Inspiron 15', 'Laptop Dell Inspiron 15', 'INSP15', 899.99, 3, 3);

-- Insert into CARRITO (cart for client1)
INSERT INTO CARRITO (cantidad_productos, total, cliente_usuario_id)
VALUES (2, 2799.98, 2);

-- Insert into DETALLE_CARRITO (cart details)
INSERT INTO DETALLE_CARRITO (cantidad, subtotal, carrito_id, producto_id)
VALUES 
    (1, 1999.99, 1, 1),  -- MacBook Pro
    (1, 799.99, 1, 2);   -- Galaxy S21

-- Insert into ORDEN_DE_VENTA (order from the cart)
INSERT INTO ORDEN_DE_VENTA (estado, fecha, total, cliente_usuario_id, direccion)
VALUES ('PAGADO', CURDATE(), 2799.98, 2, '456 Client Ave');

-- Insert into DETALLE_VENTA (order details)
INSERT INTO DETALLE_VENTA (cantidad, subtotal, orden_de_venta_id, producto_id)
VALUES 
    (1, 1999.99, 1, 1),  -- MacBook Pro
    (1, 799.99, 1, 2);   -- Galaxy S21

-- Insert into DOCUMENTO_DE_VENTAS (sales document with tax)
INSERT INTO DOCUMENTO_DE_VENTAS (numero, subtotal, impuestos, total, orden_de_venta_id)
VALUES (1001, 2799.98, 504.00, 3303.98, 1);

-- Insert into ORDEN_DEVOLUCION 
INSERT INTO orden_devolucion (fecha_registro, motivo, tipo_devolucion, documento_de_ventas_numero)
VALUES (CURRENT_DATE, 'Cliente devolvió productos defectuosos', 'REEMBOLSO', 1001);

-- Insert into PROVEEDOR (supplier)
INSERT INTO PROVEEDOR (razon_social)
VALUES ('Apple Inc.');

-- Insert into DOCUMENTO_COMPRAS (purchase document)
INSERT INTO DOCUMENTO_COMPRAS (numero, subtotal, impuestos, total, proveedor_id)
VALUES (2001, 10000.00, 0.00, 10000.00, 1);

-- Insert into LOTE (lot for the purchase)
INSERT INTO LOTE (fecha_creacion, estado, documento_compras_numero)
VALUES (CURDATE(), 'ABIERTO', 2001);

-- Insert into DETALLE_LOTE (lot details)
INSERT INTO DETALLE_LOTE (cantidad, precio_compra, lote_id, producto_id)
VALUES (10, 1500.00, 1, 1);  -- 10 MacBook Pros

-- Insert into INVENTARIO (inventory for the lot)
INSERT INTO INVENTARIO (cantidad_disponible, lote_id, producto_id)
VALUES (10, 1, 1);

-- Insert into IMPUESTO (tax type)
INSERT INTO IMPUESTO (nombre, abreviacion, tipo)
VALUES ('Value Added Tax', 'VAT', 'VENTA');

-- Insert into PERIODO (tax period)
INSERT INTO PERIODO (fecha_inicio, fecha_fin)
VALUES ('2023-01-01', '2023-12-31'),
	   ('2023-01-01', '2023-12-31');


-- Insert into IMPUESTO_PERIODO (tax rate for the period)
INSERT INTO IMPUESTO_PERIODO (periodo_id, impuesto_id, tasa, estado)
VALUES (1, 1, 0.18, 'ACTIVO');

-- Insert into IMPUESTO_DOCUMENTO (tax applied to the sales document)
INSERT INTO IMPUESTO_DOCUMENTO (monto, documento_de_ventas_numero, impuesto_periodo_periodo_id, impuesto_periodo_impuesto_id)
VALUES (504.00, 1001, 1, 1);

-- Insert into MONEDA (currencies)
INSERT INTO MONEDA (codigo, nombre)
VALUES 
    ('USD', 'US Dollar'),
    ('EUR', 'Euro');

-- Insert into MONEDA_PERIODO (exchange rates for the period)
INSERT INTO MONEDA_PERIODO (moneda_id, periodo_id, tipoCambio, estado, valor)
VALUES 
    (1, 1, 'COMPRA', 'ACTIVO', 1.00),  -- USD buy rate
    (1, 1, 'VENTA', 'ACTIVO', 1.00),   -- USD sell rate
    (2, 1, 'COMPRA', 'ACTIVO', 1.10),  -- EUR buy rate
    (2, 1, 'VENTA', 'ACTIVO', 1.20);   -- EUR sell rate

-- Insert into METODO_DE_PAGO (payment methods)
INSERT INTO METODO_DE_PAGO (nombre, descripcion, estado)
VALUES 
    ('Credit Card', 'Payment by credit card', 'ACTIVO'),
    ('PayPal', 'Payment by PayPal', 'ACTIVO');

-- Insert into PAGO (payment for the sales document)
-- Note: Using 0 for documento_de_compras_numero as a workaround since it’s NOT NULL but not applicable here
INSERT INTO PAGO (monto, fecha_pago, estado, referencia, documento_de_ventas_numero, documento_de_compras_numero, metodo_de_pago_id, moneda_periodo_id)
VALUES (3303.98, CURDATE(), 'PENDIENTE', 'CC12345', 1001, NULL, 1, 1),
		(3303.98, CURDATE(), 'PENDIENTE', 'CC12345', NULL, 2001, 2, 2);

-- Insert into LOG (log entry for admin)
INSERT INTO LOG (accion, fecha, usuario_id)
VALUES ('Logged in', CURDATE(), 1);