-- Revised dummy data for all tables in the MySQL schema
-- Generated on: 2025-04-22
-- One record per table, except DETALLE_LOTE with multiple products for one lot
-- Data respects foreign key relationships and ensures consistency in quantities and totals

-- USUARIO: Base table for users
INSERT INTO USUARIO (username, nombre, rol, correo, password)
VALUES ('juanperez', 'Juan Perez', 'ADMIN', 'juan@example.com', 'hashed_password_123');

-- ADMIN: Reference to a user
INSERT INTO ADMIN (usuario_id, fecha_ingreso)
VALUES (1, '2025-01-01');

-- CLIENTE: Reference to a user
INSERT INTO CLIENTE (usuario_id)
VALUES (1);

-- CARRITO: Shopping cart for a client
INSERT INTO CARRITO (cantidad_productos, total, cliente_usuario_id)
VALUES (2, 1199.98, 1);

-- CATEGORIA: Product category
INSERT INTO CATEGORIA (nombre)
VALUES ('Electrónica');

-- MARCA: Product brand
INSERT INTO MARCA (nombre)
VALUES ('TechBrand');

-- PRODUCTO: Products for the store (two products for DETALLE_LOTE)
INSERT INTO PRODUCTO (descripcion, sku, precio_venta, categoria_id, marca_id)
VALUES ('Smartphone 128GB', 'SM128GB', 599.99, 1, 1);
INSERT INTO PRODUCTO (descripcion, sku, precio_venta, categoria_id, marca_id)
VALUES ('Tablet 64GB', 'TB64GB', 299.99, 1, 1);

-- DETALLE_CARRITO: Cart details (matches CARRITO)
INSERT INTO DETALLE_CARRITO (cantidad, subtotal, carrito_id, producto_id)
VALUES (2, 1199.98, 1, 1);

-- ORDEN_DE_VENTA: Sales order
INSERT INTO ORDEN_DE_VENTA (estado, fecha, usuario_id, cliente_usuario_id)
VALUES ('A', '2025-04-22', 1, 1);

-- DETALLE_VENTA: Sales order details
INSERT INTO DETALLE_VENTA (cantidad, precio_producto, cantidad_devuelta, orden_de_venta_id, producto_id)
VALUES (2, 599.99, 0, 1, 1);

-- DOCUMENTO_DE_VENTAS: Sales document (subtotal = 2 * 599.99, impuestos = 18%, total = subtotal + impuestos)
INSERT INTO DOCUMENTO_DE_VENTAS (subtotal, impuestos, total, total_pagado, total_pendiente, orden_de_venta_id)
VALUES (1199.98, 216.00, 1415.98, 1415.98, 0.00, 1);

-- PROVEEDOR: Supplier
INSERT INTO PROVEEDOR (razon_social)
VALUES ('TechSupplier SAC');

-- DOCUMENTO_COMPRAS: Purchase document (subtotal based on DETALLE_LOTE)
INSERT INTO DOCUMENTO_COMPRAS (subtotal, impuestos, total, proveedor_id)
VALUES (85000.00, 15300.00, 100300.00, 1);

-- LOTE: Product lot
INSERT INTO LOTE (fecha_creacion, estado, documento_compras_numero)
VALUES ('2025-04-22', 'A', 1);

-- DETALLE_LOTE: Multiple products for one lot (two products)
INSERT INTO DETALLE_LOTE (cantidad, precio_compra, lote_id, id_pt, producto_id)
VALUES (100, 400.00, 1, 1, 1); -- 100 * 400.00 = 40000.00
INSERT INTO DETALLE_LOTE (cantidad, precio_compra, lote_id, id_pt, producto_id)
VALUES (150, 300.00, 1, 1, 2); -- 150 * 300.00 = 45000.00
-- Total subtotal = 40000.00 + 45000.00 = 85000.00 (matches DOCUMENTO_COMPRAS)

-- INVENTARIO: Inventory record
INSERT INTO INVENTARIO (cantidad_disponible, cantidad_reservada, lote_id, producto_id)
VALUES (100, 10, 1, 1);

-- IMPUESTO: Tax type
INSERT INTO IMPUESTO (nombre, abreviacion, tipo)
VALUES ('IGV', 'IGV', 'V');

-- PERIODO: Tax period
INSERT INTO PERIODO (fecha_inicio, fecha_fin)
VALUES ('2025-01-01', '2025-12-31');

-- IMPUESTO_PERIODO: Tax rate for a period
INSERT INTO IMPUESTO_PERIODO (periodo_id, impuesto_id, tasa, estado)
VALUES (1, 1, 0.18, 'A');

-- IMPUESTO_DOCUMENTO: Tax applied to documents
INSERT INTO IMPUESTO_DOCUMENTO (monto, documento_de_ventas_numero, documento_compras_numero, impuesto_periodo_periodo_id, impuesto_periodo_impuesto_id)
VALUES (216.00, 1, NULL, 1, 1);

-- MONEDA: Currency
INSERT INTO MONEDA (codigo, nombre)
VALUES ('US', 'Dolar EEUU');

-- MONEDA_PERIODO: Exchange rate for a period
INSERT INTO MONEDA_PERIODO (moneda_id, periodo_id, tipoCambio, estado, valor)
VALUES (1, 1, 'V', 'A', 3.75);

-- METODO_DE_PAGO: Payment method
INSERT INTO METODO_DE_PAGO (nombre, descripcion, estado)
VALUES ('Tarjeta Crédito', 'Pago con tarjeta', 'A');

-- PAGO: Payment record
INSERT INTO PAGO (monto, fecha_pago, estado, referencia, documento_de_ventas_numero, metodo_de_pago_id, moneda_periodo_moneda_id, moneda_periodo_periodo_id)
VALUES (1415.98, '2025-04-22', 'A', 'TX12345', 1, 1, 1, 1);

-- LOG: User action log
INSERT INTO LOG (accion, fecha, usuario_id)
VALUES ('Inicio de sesión', '2025-04-22', 1);