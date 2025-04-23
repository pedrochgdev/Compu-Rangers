-- USUARIO: Two users (one for ADMIN, one for CLIENTE)
INSERT INTO USUARIO (username, nombre, telefono, correo, direccion, password)
VALUES ('adminuser', 'Admin User', '987654321', 'admin@example.com', 'Av. Admin 123', 'hashed_password_admin');
INSERT INTO USUARIO (username, nombre, telefono, correo, direccion, password)
VALUES ('clientuser', 'Client User', '987654322', 'client@example.com', 'Av. Client 456', 'hashed_password_client');

-- ADMIN: References USUARIO ID=1
INSERT INTO ADMIN (usuario_id, fecha_ingreso)
VALUES (1, '2025-01-01');

-- CLIENTE: References USUARIO ID=2
INSERT INTO CLIENTE (usuario_id, direccion)
VALUES (2, 'Calle Secundaria 456');

-- CATEGORIA: Product category
INSERT INTO CATEGORIA (nombre)
VALUES ('Electrónica');

-- MARCA: Product brand
INSERT INTO MARCA (nombre)
VALUES ('TechBrand');

-- PRODUCTO: Products for the store (two products for DETALLE_LOTE and DETALLE_CARRITO)
INSERT INTO PRODUCTO (nombre, descripcion, sku, precio_venta, categoria_id, marca_id)
VALUES ('Smartphone 128GB', 'Smartphone de alta gama', 'SM128GB', 599.99, 1, 1);
INSERT INTO PRODUCTO (nombre, descripcion, sku, precio_venta, categoria_id, marca_id)
VALUES ('Tablet 64GB', 'Tablet ligera', 'TB64GB', 299.99, 1, 1);

-- CARRITO: Shopping cart for CLIENTE (ID=2)
INSERT INTO CARRITO (cantidad_productos, total, cliente_usuario_id)
VALUES (3, 1499.97, 2); -- 2 Smartphone (1199.98) + 1 Tablet (299.99)

-- DETALLE_CARRITO: Cart details (matches CARRITO total and cantidad_productos)
INSERT INTO DETALLE_CARRITO (cantidad, subtotal, carrito_id, producto_id)
VALUES (2, 1199.98, 1, 1); -- 2 * 599.99
INSERT INTO DETALLE_CARRITO (cantidad, subtotal, carrito_id, producto_id)
VALUES (1, 299.99, 1, 2); -- 1 * 299.99

-- ORDEN_DE_VENTA: Sales order for CLIENTE (ID=2)
INSERT INTO ORDEN_DE_VENTA (estado, fecha, total, cliente_usuario_id, direccion)
VALUES ('PAGADO', '2025-04-22', 1415.98, 2, 'Calle Secundaria 456');

-- DETALLE_VENTA: Sales order details
INSERT INTO DETALLE_VENTA (cantidad, precio_producto, cantidad_devuelta, orden_de_venta_id, producto_id)
VALUES (2, 599.99, 0, 1, 1); -- Matches ORDEN_DE_VENTA total (2 * 599.99 + taxes)

-- DOCUMENTO_DE_VENTAS: Sales document (subtotal = 2 * 599.99, impuestos = 18%)
INSERT INTO DOCUMENTO_DE_VENTAS (subtotal, impuestos, total, total_pagado, orden_de_venta_id)
VALUES (1199.98, 216.00, 1415.98, 1415.98, 1);

-- ORDEN_DEVOLUCION: Return order
INSERT INTO ORDEN_DEVOLUCION (motivo, fecha_registro, tipo_devolucion, documento_de_ventas_numero)
VALUES ('Producto defectuoso', '2025-04-23', 'R', 1);

-- DETALLE_DEVOLUCION: Return details
INSERT INTO DETALLE_DEVOLUCION (cantidad, detalle_venta_id, orden_devolucion_id)
VALUES (1, 1, 1);

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
INSERT INTO DETALLE_LOTE (cantidad, precio_compra, lote_id, producto_id)
VALUES (100, 400.00, 1, 1); -- 100 * 400.00 = 40000.00
INSERT INTO DETALLE_LOTE (cantidad, precio_compra, lote_id, producto_id)
VALUES (150, 300.00, 1, 2); -- 150 * 300.00 = 45000.00
-- Total subtotal = 40000.00 + 45000.00 = 85000.00 (matches DOCUMENTO_COMPRAS)

-- INVENTARIO: Inventory record
INSERT INTO INVENTARIO (cantidad_disponible, lote_id, producto_id)
VALUES (100, 1, 1);

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
VALUES ('USD', 'Dolar EEUU');

-- MONEDA_PERIODO: Exchange rate for a period
INSERT INTO MONEDA_PERIODO (moneda_id, periodo_id, tipoCambio, estado, valor)
VALUES (1, 1, 'V', 'A', 3.75);

-- METODO_DE_PAGO: Payment method
INSERT INTO METODO_DE_PAGO (nombre, descripcion, estado)
VALUES ('Tarjeta Crédito', 'Pago con tarjeta', 'A');

-- PAGO: Payment record for CLIENTE (references DOCUMENTO_DE_VENTAS and DOCUMENTO_COMPRAS)
INSERT INTO PAGO (monto, fecha_pago, estado, referencia, documento_de_ventas_numero, documento_de_compras_numero, metodo_de_pago_id, moneda_periodo_moneda_id, moneda_periodo_periodo_id)
VALUES (1415.98, '2025-04-22', 'P', 'TX12345', 1, 1, 1, 1, 1);

-- LOG: Admin action log (references ADMIN user ID=1)
INSERT INTO LOG (accion, fecha, usuario_id)
VALUES ('Inicio de sesión', '2025-04-22', 1);