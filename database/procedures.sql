/* DROPS */

DROP PROCEDURE IF EXISTS add_categoria;
DROP PROCEDURE IF EXISTS update_categoria;
DROP PROCEDURE IF EXISTS delete_categoria;
DROP PROCEDURE IF EXISTS search_categoria;
DROP PROCEDURE IF EXISTS get_all_categorias;
DROP PROCEDURE IF EXISTS get_all_categorias_from_padre;
DROP PROCEDURE IF EXISTS add_marca;
DROP PROCEDURE IF EXISTS update_marca;
DROP PROCEDURE IF EXISTS delete_marca;
DROP PROCEDURE IF EXISTS search_marca;
DROP PROCEDURE IF EXISTS get_all_marcas;
DROP PROCEDURE IF EXISTS add_producto;
DROP PROCEDURE IF EXISTS update_producto;
DROP PROCEDURE IF EXISTS delete_producto;
DROP PROCEDURE IF EXISTS search_producto;
DROP PROCEDURE IF EXISTS get_all_productos;
DROP PROCEDURE IF EXISTS add_detalle_lote;
DROP PROCEDURE IF EXISTS update_detalle_lote;
DROP PROCEDURE IF EXISTS delete_detalle_lote;
DROP PROCEDURE IF EXISTS search_detalle_lote;
DROP PROCEDURE IF EXISTS get_all_detalle_lote;
DROP PROCEDURE IF EXISTS get_all_detalle_lote_from_lote;
DROP PROCEDURE IF EXISTS add_inventario;
DROP PROCEDURE IF EXISTS update_inventario;
DROP PROCEDURE IF EXISTS delete_inventario;
DROP PROCEDURE IF EXISTS search_inventario;
DROP PROCEDURE IF EXISTS get_all_inventario;
DROP PROCEDURE IF EXISTS get_all_inventario_from_lote;
DROP PROCEDURE IF EXISTS add_lote;
DROP PROCEDURE IF EXISTS update_lote;
DROP PROCEDURE IF EXISTS delete_lote;
DROP PROCEDURE IF EXISTS search_lote;
DROP PROCEDURE IF EXISTS get_all_lote;
DROP PROCEDURE IF EXISTS add_proveedor;
DROP PROCEDURE IF EXISTS update_proveedor;
DROP PROCEDURE IF EXISTS delete_proveedor;
DROP PROCEDURE IF EXISTS search_proveedor;
DROP PROCEDURE IF EXISTS get_all_proveedor;
DROP PROCEDURE IF EXISTS add_documento_compras;
DROP PROCEDURE IF EXISTS update_documento_compras;
DROP PROCEDURE IF EXISTS delete_documento_compras;
DROP PROCEDURE IF EXISTS search_documento_compras;
DROP PROCEDURE IF EXISTS get_all_documento_compras;
DROP PROCEDURE IF EXISTS get_documentos_compras_by_proveedor;
DROP PROCEDURE IF EXISTS add_carrito;
DROP PROCEDURE IF EXISTS update_carrito;
DROP PROCEDURE IF EXISTS delete_carrito;
DROP PROCEDURE IF EXISTS search_carrito;
DROP PROCEDURE IF EXISTS get_all_carrito;
DROP PROCEDURE IF EXISTS add_item_carrito;
DROP PROCEDURE IF EXISTS update_item_carrito;
DROP PROCEDURE IF EXISTS delete_item_carrito;
DROP PROCEDURE IF EXISTS search_item_carrito;
DROP PROCEDURE IF EXISTS get_all_item_carrito;
DROP PROCEDURE IF EXISTS get_all_item_from_carrito;
DROP PROCEDURE IF EXISTS add_orden_de_venta;
DROP PROCEDURE IF EXISTS update_orden_de_venta;
DROP PROCEDURE IF EXISTS delete_orden_de_venta;
DROP PROCEDURE IF EXISTS search_orden_de_venta;
DROP PROCEDURE IF EXISTS get_all_orden_de_venta;
DROP PROCEDURE IF EXISTS get_all_orden_de_venta_from_cliente;
DROP PROCEDURE IF EXISTS add_detalle_venta;
DROP PROCEDURE IF EXISTS update_detalle_venta;
DROP PROCEDURE IF EXISTS delete_detalle_venta;
DROP PROCEDURE IF EXISTS search_detalle_venta;
DROP PROCEDURE IF EXISTS get_all_detalle_venta;
DROP PROCEDURE IF EXISTS get_all_detalles_from_orden_venta;
DROP PROCEDURE IF EXISTS add_orden_devolucion;
DROP PROCEDURE IF EXISTS update_orden_devolucion;
DROP PROCEDURE IF EXISTS delete_orden_devolucion;
DROP PROCEDURE IF EXISTS search_orden_devolucion;
DROP PROCEDURE IF EXISTS get_all_orden_devolucion;
DROP PROCEDURE IF EXISTS get_all_ordenes_devolucion_from_doc_venta;
DROP PROCEDURE IF EXISTS add_detalle_devolucion;
DROP PROCEDURE IF EXISTS update_detalle_devolucion;
DROP PROCEDURE IF EXISTS delete_detalle_devolucion;
DROP PROCEDURE IF EXISTS search_detalle_devolucion;
DROP PROCEDURE IF EXISTS get_all_detalle_devolucion;
DROP PROCEDURE IF EXISTS get_all_detalles_devolucion_by_orden;
DROP PROCEDURE IF EXISTS add_documento_de_ventas;
DROP PROCEDURE IF EXISTS update_documento_de_ventas;
DROP PROCEDURE IF EXISTS delete_documento_de_ventas;
DROP PROCEDURE IF EXISTS search_documento_de_ventas;
DROP PROCEDURE IF EXISTS get_all_documento_de_ventas;
DROP PROCEDURE IF EXISTS get_documentos_by_orden_venta;
DROP PROCEDURE IF EXISTS add_log;
DROP PROCEDURE IF EXISTS update_log;
DROP PROCEDURE IF EXISTS delete_log;
DROP PROCEDURE IF EXISTS search_log;
DROP PROCEDURE IF EXISTS get_all_logs;
DROP PROCEDURE IF EXISTS get_all_logs_by_usuario;
DROP PROCEDURE IF EXISTS add_impuesto;
DROP PROCEDURE IF EXISTS update_impuesto;
DROP PROCEDURE IF EXISTS delete_impuesto;
DROP PROCEDURE IF EXISTS search_impuesto;
DROP PROCEDURE IF EXISTS get_all_impuesto;
DROP PROCEDURE IF EXISTS add_metodo_pago;
DROP PROCEDURE IF EXISTS update_metodo_pago;
DROP PROCEDURE IF EXISTS delete_metodo_pago;
DROP PROCEDURE IF EXISTS search_metodo_pago;
DROP PROCEDURE IF EXISTS get_all_metodo_pago;
DROP PROCEDURE IF EXISTS add_moneda;
DROP PROCEDURE IF EXISTS update_moneda;
DROP PROCEDURE IF EXISTS delete_moneda;
DROP PROCEDURE IF EXISTS search_moneda;
DROP PROCEDURE IF EXISTS get_all_moneda;
DROP PROCEDURE IF EXISTS add_pago;
DROP PROCEDURE IF EXISTS update_pago;
DROP PROCEDURE IF EXISTS delete_pago;
DROP PROCEDURE IF EXISTS search_pago;
DROP PROCEDURE IF EXISTS get_all_pago;
DROP PROCEDURE IF EXISTS get_pagos_by_documento_venta;
DROP PROCEDURE IF EXISTS get_pago_by_documento_compra;
DROP PROCEDURE IF EXISTS add_impuesto_periodo;
DROP PROCEDURE IF EXISTS update_impuesto_periodo;
DROP PROCEDURE IF EXISTS delete_impuesto_periodo;
DROP PROCEDURE IF EXISTS search_impuesto_periodo;
DROP PROCEDURE IF EXISTS get_all_impuesto_periodo;
DROP PROCEDURE IF EXISTS add_moneda_periodo;
DROP PROCEDURE IF EXISTS update_moneda_periodo;
DROP PROCEDURE IF EXISTS delete_moneda_periodo;
DROP PROCEDURE IF EXISTS search_moneda_periodo;
DROP PROCEDURE IF EXISTS get_all_moneda_periodo;
DROP PROCEDURE IF EXISTS add_periodo;
DROP PROCEDURE IF EXISTS update_periodo;
DROP PROCEDURE IF EXISTS delete_periodo;
DROP PROCEDURE IF EXISTS search_periodo;
DROP PROCEDURE IF EXISTS get_all_periodo;

/* CATALOG */
/* PROCEDURES CATEGORIA */

DELIMITER //
CREATE PROCEDURE add_categoria(
    OUT generated_id INT,
    IN nombre_in VARCHAR(100),
    IN padre_id_in INT
)
BEGIN
    INSERT INTO CATEGORIA (nombre, padre_id)
    VALUES (nombre_in, padre_id_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_categoria(
    IN id_in INT,
    IN nombre_in VARCHAR(100),
    IN padre_id_in INT
)
BEGIN
    UPDATE CATEGORIA
    SET nombre = nombre_in,
        padre_id = padre_id_in
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_categoria(
    IN id_in INT
)
BEGIN
    DELETE FROM CATEGORIA
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_categoria(
    IN id_in INT
)
BEGIN
    SELECT *
    FROM CATEGORIA
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_categorias()
BEGIN
    SELECT *
    FROM CATEGORIA;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_categorias_from_padre(
    IN padre_id_in INT
)
BEGIN
    SELECT *
    FROM CATEGORIA
    WHERE padre_id = padre_id_in;
END;
//
DELIMITER ;

/* PROCEDURES MARCA */

DELIMITER //
CREATE PROCEDURE add_marca(
    OUT generated_id INT,
    IN nombre_in VARCHAR(100)
)
BEGIN
    INSERT INTO MARCA (nombre)
    VALUES (nombre_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_marca(
    IN id_in INT,
    IN nombre_in VARCHAR(100)
)
BEGIN
    UPDATE MARCA
    SET nombre = nombre_in
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_marca(
    IN id_in INT
)
BEGIN
    DELETE FROM MARCA
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_marca(
    IN id_in INT
)
BEGIN
    SELECT *
    FROM MARCA
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_marcas()
BEGIN
    SELECT *
    FROM MARCA;
END;
//
DELIMITER ;

/* PROCEDURES PRODUCTO */

DELIMITER //
CREATE PROCEDURE add_producto(
    OUT generated_id INT,
    IN sku_in VARCHAR(50),
    IN nombre_in VARCHAR(100),
    IN descripcion_in TEXT,
    IN precio_venta_in DECIMAL(10, 2),
    IN categoria_id_in INT,
    IN marca_id_in INT
)
BEGIN
    INSERT INTO PRODUCTO (sku, nombre, descripcion, precio_venta, categoria_id, marca_id)
    VALUES (sku_in, nombre_in, descripcion_in, precio_venta_in, categoria_id_in, marca_id_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_producto(
    IN id_in INT,
    IN sku_in VARCHAR(50),
    IN nombre_in VARCHAR(100),
    IN descripcion_in TEXT,
    IN precio_venta_in DECIMAL(10, 2),
    IN categoria_id_in INT,
    IN marca_id_in INT
)
BEGIN
    UPDATE PRODUCTO
    SET sku = sku_in,
        nombre = nombre_in,
        descripcion = descripcion_in,
        precio_venta = precio_venta_in,
        categoria_id = categoria_id_in,
        marca_id = marca_id_in
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_producto(
    IN id_in INT
)
BEGIN
    DELETE FROM PRODUCTO
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_producto(
    IN id_in INT
)
BEGIN
    SELECT p.*, c.id AS cid, m.id AS mid
    FROM PRODUCTO p
    JOIN CATEGORIA c ON p.categoria_id = c.id
    JOIN MARCA m ON p.marca_id = m.id
    WHERE p.id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_productos()
BEGIN
    SELECT p.*, c.id AS cid, m.id AS mid
    FROM PRODUCTO p
    JOIN CATEGORIA c ON p.categoria_id = c.id
    JOIN MARCA m ON p.marca_id = m.id;
END;
//
DELIMITER ;

/* INVENTORY */
/* PROCEDURES DETALLE_LOTE */

DELIMITER //
CREATE PROCEDURE add_detalle_lote(
    OUT generated_id INT,
    IN cantidad_in INT,
    IN precio_compra_in DECIMAL(10, 2),
    IN lote_id_in INT,
    IN producto_id_in INT
)
BEGIN
    INSERT INTO DETALLE_LOTE (cantidad, precio_compra, lote_id, producto_id)
    VALUES (cantidad_in, precio_compra_in, lote_id_in, producto_id_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_detalle_lote(
    IN id_in INT,
    IN cantidad_in INT,
    IN precio_compra_in DECIMAL(10, 2),
    IN lote_id_in INT,
    IN producto_id_in INT
)
BEGIN
    UPDATE DETALLE_LOTE
    SET cantidad = cantidad_in,
        precio_compra = precio_compra_in,
        lote_id = lote_id_in,
        producto_id = producto_id_in
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_detalle_lote(
    IN id_in INT
)
BEGIN
    DELETE FROM DETALLE_LOTE
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_detalle_lote(
    IN id_in INT
)
BEGIN
    SELECT * FROM DETALLE_LOTE
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_detalle_lote()
BEGIN
    SELECT * FROM DETALLE_LOTE;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_detalle_lote_from_lote(
    IN lote_id_in INT
)
BEGIN
    SELECT * FROM DETALLE_LOTE
    WHERE lote_id = lote_id_in;
END;
//
DELIMITER ;

/* PROCEDURES INVENTARIO */

DELIMITER //
CREATE PROCEDURE add_inventario(
    OUT generated_id INT,
    IN cantidad_disponible_in INT,
    IN lote_id_in INT,
    IN producto_id_in INT
)
BEGIN
    INSERT INTO INVENTARIO (cantidad_disponible, lote_id, producto_id)
    VALUES (cantidad_disponible_in, lote_id_in, producto_id_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_inventario(
    IN id_in INT,
    IN cantidad_disponible_in INT,
    IN lote_id_in INT,
    IN producto_id_in INT
)
BEGIN
    UPDATE INVENTARIO
    SET cantidad_disponible = cantidad_disponible_in,
        lote_id = lote_id_in,
        producto_id = producto_id_in
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_inventario(
    IN id_in INT
)
BEGIN
    DELETE FROM INVENTARIO
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_inventario(
    IN id_in INT
)
BEGIN
    SELECT * FROM INVENTARIO
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_inventario()
BEGIN
    SELECT * FROM INVENTARIO;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_inventario_from_lote(
    IN lote_id_in INT
)
BEGIN
    SELECT * FROM INVENTARIO
    WHERE lote_id = lote_id_in;
END;
//
DELIMITER ;

/* PROCEDURES LOTE */

DELIMITER //
CREATE PROCEDURE add_lote(
    OUT generated_id INT,
    IN fecha_creacion_in DATE,
    IN estado_in VARCHAR(50),
    IN documento_compras_numero_in INT
)
BEGIN
    INSERT INTO LOTE (fecha_creacion, estado, documento_compras_numero)
    VALUES (fecha_creacion_in, estado_in, documento_compras_numero_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_lote(
    IN id_in INT,
    IN fecha_creacion_in DATE,
    IN estado_in VARCHAR(50),
    IN documento_compras_numero_in INT
)
BEGIN
    UPDATE LOTE
    SET fecha_creacion = fecha_creacion_in,
        estado = estado_in,
        documento_compras_numero = documento_compras_numero_in
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_lote(
    IN id_in INT
)
BEGIN
    DELETE FROM LOTE
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_lote(
    IN id_in INT
)
BEGIN
    SELECT * FROM LOTE
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_lote()
BEGIN
    SELECT * FROM LOTE;
END;
//
DELIMITER ;

/* PROCEDURES PROVEEDOR */

DELIMITER //
CREATE PROCEDURE add_proveedor(
    OUT generated_id INT,
    IN razon_social_in VARCHAR(100)
)
BEGIN
    INSERT INTO PROVEEDOR (razon_social)
    VALUES (razon_social_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_proveedor(
    IN id_in INT,
    IN razon_social_in VARCHAR(100)
)
BEGIN
    UPDATE PROVEEDOR
    SET razon_social = razon_social_in
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_proveedor(
    IN id_in INT
)
BEGIN
    DELETE FROM PROVEEDOR
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_proveedor(
    IN id_in INT
)
BEGIN
    SELECT * FROM PROVEEDOR
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_proveedor()
BEGIN
    SELECT * FROM PROVEEDOR;
END;
//
DELIMITER ;

/* PROCEDURES DOCUMENTO_COMPRAS */

DELIMITER //
CREATE PROCEDURE add_documento_compras(
    OUT generated_id INT,
    OUT generated_numero INT,
    IN subtotal_in DECIMAL(10,2),
    IN impuestos_in DECIMAL(10,2),
    IN total_in DECIMAL(10,2),
    IN proveedor_id_in INT
)
BEGIN
    SELECT IFNULL(MAX(numero), 0) + 1 INTO generated_numero FROM DOCUMENTO_COMPRAS;

    INSERT INTO DOCUMENTO_COMPRAS (numero, subtotal, impuestos, total, proveedor_id)
    VALUES (generated_numero, subtotal_in, impuestos_in, total_in, proveedor_id_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_documento_compras(
    IN numero_in INT,
    IN subtotal_in DECIMAL(10,2),
    IN impuestos_in DECIMAL(10,2),
    IN total_in DECIMAL(10,2),
    IN proveedor_id_in INT
)
BEGIN
    UPDATE DOCUMENTO_COMPRAS
    SET subtotal = subtotal_in,
        impuestos = impuestos_in,
        total = total_in,
        proveedor_id = proveedor_id_in
    WHERE numero = numero_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_documento_compras(
    IN numero_in INT
)
BEGIN
    DELETE FROM DOCUMENTO_COMPRAS
    WHERE numero = numero_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_documento_compras(
    IN numero_in INT
)
BEGIN
    SELECT *
    FROM DOCUMENTO_COMPRAS
    WHERE numero = numero_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_documento_compras()
BEGIN
    SELECT *
    FROM DOCUMENTO_COMPRAS
    ORDER BY numero DESC;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_documentos_compras_by_proveedor(
    IN proveedor_id_in INT
)
BEGIN
    SELECT *
    FROM DOCUMENTO_COMPRAS
    WHERE proveedor_id = proveedor_id_in
    ORDER BY numero DESC;
END;
//
DELIMITER ;

/* SALES */
/* PROCEDURES CARRITO */

DELIMITER //
CREATE PROCEDURE add_carrito(
    OUT generated_id INT,
    IN total_in DECIMAL(10,2),
    IN cantidad_productos_in INT,
    IN cliente_usuario_id_in INT
)
BEGIN
    INSERT INTO CARRITO (total, cantidad_productos, cliente_usuario_id)
    VALUES (total_in, cantidad_productos_in, cliente_usuario_id_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_carrito(
    IN id_in INT,
    IN total_in DECIMAL(10,2),
    IN cantidad_productos_in INT,
    IN cliente_usuario_id_in INT
)
BEGIN
    UPDATE CARRITO
    SET total = total_in,
        cantidad_productos = cantidad_productos_in,
        cliente_usuario_id = cliente_usuario_id_in
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_carrito(
    IN id_in INT
)
BEGIN
    DELETE FROM CARRITO
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_carrito(
    IN id_in INT
)
BEGIN
    SELECT * FROM CARRITO
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_carrito()
BEGIN
    SELECT * FROM CARRITO;
END;
//
DELIMITER ;

/* PROCEDURES DETALLE_CARRITO */

DELIMITER //
CREATE PROCEDURE add_item_carrito(
    OUT generated_id INT,
    IN producto_id_in INT,
    IN cantidad_in INT,
    IN subtotal_in DECIMAL(10,2),
    IN carrito_id_in INT
)
BEGIN
    INSERT INTO DETALLE_CARRITO (producto_id, cantidad, subtotal, carrito_id)
    VALUES (producto_id_in, cantidad_in, subtotal_in, carrito_id_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_item_carrito(
    IN id_in INT,
    IN producto_id_in INT,
    IN cantidad_in INT,
    IN subtotal_in DECIMAL(10,2),
    IN carrito_id_in INT
)
BEGIN
    UPDATE DETALLE_CARRITO
    SET producto_id = producto_id_in,
        cantidad = cantidad_in,
        subtotal = subtotal_in,
        carrito_id = carrito_id_in
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_item_carrito(
    IN id_in INT
)
BEGIN
    DELETE FROM DETALLE_CARRITO
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_item_carrito(
    IN id_in INT
)
BEGIN
    SELECT * FROM DETALLE_CARRITO
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_item_carrito()
BEGIN
    SELECT * FROM DETALLE_CARRITO;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_item_from_carrito(
    IN carrito_id_in INT
)
BEGIN
    SELECT * FROM DETALLE_CARRITO
    WHERE carrito_id = carrito_id_in;
END;
//
DELIMITER ;

/* PROCEDURES ORDEN_DE_VENTA */

DELIMITER //
CREATE PROCEDURE add_orden_de_venta(
    OUT generated_id INT,
    IN estado_in VARCHAR(50),
    IN fecha_in DATE,
    IN total_in DECIMAL(10,2),
    IN cliente_usuario_id_in INT,
    IN direccion_in VARCHAR(255)
)
BEGIN
    INSERT INTO ORDEN_DE_VENTA (estado, fecha, total, cliente_usuario_id, direccion)
    VALUES (estado_in, fecha_in, total_in, cliente_usuario_id_in, direccion_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_orden_de_venta(
    IN id_in INT,
    IN estado_in VARCHAR(50),
    IN fecha_in DATE,
    IN total_in DECIMAL(10,2),
    IN cliente_usuario_id_in INT,
    IN direccion_in VARCHAR(255)
)
BEGIN
    UPDATE ORDEN_DE_VENTA
    SET estado = estado_in,
        fecha = fecha_in,
        total = total_in,
        cliente_usuario_id = cliente_usuario_id_in,
        direccion = direccion_in
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_orden_de_venta(
    IN id_in INT
)
BEGIN
    DELETE FROM ORDEN_DE_VENTA
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_orden_de_venta(
    IN id_in INT
)
BEGIN
    SELECT * FROM ORDEN_DE_VENTA
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_orden_de_venta()
BEGIN
    SELECT * FROM ORDEN_DE_VENTA;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_orden_de_venta_from_cliente(
    IN cliente_usuario_id_in INT
)
BEGIN
    SELECT * FROM ORDEN_DE_VENTA
    WHERE cliente_usuario_id = cliente_usuario_id_in;
END;
//
DELIMITER ;

/* PROCEDURES DETALLE_VENTA */

DELIMITER //
CREATE PROCEDURE add_detalle_venta(
    OUT generated_id INT,
    IN cantidad_in INT,
    IN subtotal_in DECIMAL(10,2),
    IN cantidad_devuelta_in INT,
    IN orden_de_venta_id_in INT,
    IN producto_id_in INT
)
BEGIN
    INSERT INTO DETALLE_VENTA (cantidad, subtotal, cantidad_devuelta, orden_de_venta_id, producto_id)
    VALUES (cantidad_in, subtotal_in, cantidad_devuelta_in, orden_de_venta_id_in, producto_id_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_detalle_venta(
    IN id_in INT,
    IN cantidad_in INT,
    IN subtotal_in DECIMAL(10,2),
    IN cantidad_devuelta_in INT,
    IN orden_de_venta_id_in INT,
    IN producto_id_in INT
)
BEGIN
    UPDATE DETALLE_VENTA
    SET cantidad = cantidad_in,
        subtotal = subtotal_in,
        cantidad_devuelta = cantidad_devuelta_in,
        orden_de_venta_id = orden_de_venta_id_in,
        producto_id = producto_id_in
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_detalle_venta(
    IN id_in INT
)
BEGIN
    DELETE FROM DETALLE_VENTA WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_detalle_venta(
    IN id_in INT
)
BEGIN
    SELECT * FROM DETALLE_VENTA WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_detalle_venta()
BEGIN
    SELECT * FROM DETALLE_VENTA;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_detalles_from_orden_venta(
    IN orden_de_venta_id_in INT
)
BEGIN
    SELECT * FROM DETALLE_VENTA WHERE orden_de_venta_id = orden_de_venta_id_in;
END;
//
DELIMITER ;

/* PROCEDURES ORDEN_DEVOLUCION */

DELIMITER //
CREATE PROCEDURE add_orden_devolucion(
    OUT generated_id INT,
    IN motivo_in VARCHAR(200),
    IN fecha_registro_in DATE,
    IN tipo_devolucion_in CHAR(1),
    IN documento_de_ventas_numero_in INT
)
BEGIN
    INSERT INTO ORDEN_DEVOLUCION (motivo, fecha_registro, tipo_devolucion, documento_de_ventas_numero)
    VALUES (motivo_in, fecha_registro_in, tipo_devolucion_in, documento_de_ventas_numero_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_orden_devolucion(
    IN id_in INT,
    IN motivo_in VARCHAR(200),
    IN fecha_registro_in DATE,
    IN tipo_devolucion_in CHAR(1),
    IN documento_de_ventas_numero_in INT
)
BEGIN
    UPDATE ORDEN_DEVOLUCION
    SET motivo = motivo_in,
        fecha_registro = fecha_registro_in,
        tipo_devolucion = tipo_devolucion_in,
        documento_de_ventas_numero = documento_de_ventas_numero_in
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_orden_devolucion(
    IN id_in INT
)
BEGIN
    DELETE FROM ORDEN_DEVOLUCION WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_orden_devolucion(
    IN id_in INT
)
BEGIN
    SELECT * FROM ORDEN_DEVOLUCION WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_orden_devolucion()
BEGIN
    SELECT * FROM ORDEN_DEVOLUCION;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_ordenes_devolucion_from_doc_venta(
    IN documento_de_ventas_numero_in INT
)
BEGIN
    SELECT * FROM ORDEN_DEVOLUCION WHERE documento_de_ventas_numero = documento_de_ventas_numero_in;
END;
//
DELIMITER ;

/* PROCEDURES DETALLE_DEVOLUCION */

DELIMITER //
CREATE PROCEDURE add_detalle_devolucion(
    OUT generated_id INT,
    IN cantidad_in INT,
    IN detalle_venta_id_in INT,
    IN orden_devolucion_id_in INT
)
BEGIN
    INSERT INTO DETALLE_DEVOLUCION (cantidad, detalle_venta_id, orden_devolucion_id)
    VALUES (cantidad_in, detalle_venta_id_in, orden_devolucion_id_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_detalle_devolucion(
    IN id_in INT,
    IN cantidad_in INT,
    IN detalle_venta_id_in INT,
    IN orden_devolucion_id_in INT
)
BEGIN
    UPDATE DETALLE_DEVOLUCION
    SET cantidad = cantidad_in,
        detalle_venta_id = detalle_venta_id_in,
        orden_devolucion_id = orden_devolucion_id_in
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_detalle_devolucion(
    IN id_in INT
)
BEGIN
    DELETE FROM DETALLE_DEVOLUCION WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_detalle_devolucion(
    IN id_in INT
)
BEGIN
    SELECT * FROM DETALLE_DEVOLUCION WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_detalle_devolucion()
BEGIN
    SELECT * FROM DETALLE_DEVOLUCION;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_detalles_devolucion_by_orden(
    IN orden_devolucion_id_in INT
)
BEGIN
    SELECT * FROM DETALLE_DEVOLUCION
    WHERE orden_devolucion_id = orden_devolucion_id_in;
END;
//
DELIMITER ;

/* PROCEDURES DOCUMENTO_DE_VENTAS */

DELIMITER //
CREATE PROCEDURE add_documento_de_ventas(
    OUT generated_id INT,
    OUT generated_numero INT,
    IN subtotal_in DECIMAL(10,2),
    IN impuestos_in DECIMAL(8,2),
    IN total_in DECIMAL(10,2),
    IN total_pagado_in DECIMAL(10,2),
    IN orden_de_venta_id_in INT
)
BEGIN
    DECLARE next_numero INT;
    
    SELECT IFNULL(MAX(numero), 0) + 1 INTO next_numero FROM DOCUMENTO_DE_VENTAS;
    
    INSERT INTO DOCUMENTO_DE_VENTAS (numero, subtotal, impuestos, total, total_pagado, orden_de_venta_id)
    VALUES (next_numero, subtotal_in, impuestos_in, total_in, total_pagado_in, orden_de_venta_id_in);

    SET generated_id = LAST_INSERT_ID();
    SET generated_numero = next_numero;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_documento_de_ventas(
    IN numero_in INT,
    IN subtotal_in DECIMAL(10,2),
    IN impuestos_in DECIMAL(8,2),
    IN total_in DECIMAL(10,2),
    IN total_pagado_in DECIMAL(10,2),
    IN orden_de_venta_id_in INT
)
BEGIN
    UPDATE DOCUMENTO_DE_VENTAS
    SET subtotal = subtotal_in,
        impuestos = impuestos_in,
        total = total_in,
        total_pagado = total_pagado_in,
        orden_de_venta_id = orden_de_venta_id_in
    WHERE numero = numero_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_documento_de_ventas(
    IN numero_in INT
)
BEGIN
    DELETE FROM DOCUMENTO_DE_VENTAS WHERE numero = numero_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_documento_de_ventas(
    IN numero_in INT
)
BEGIN
    SELECT * FROM DOCUMENTO_DE_VENTAS WHERE numero = numero_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_documento_de_ventas()
BEGIN
    SELECT * FROM DOCUMENTO_DE_VENTAS;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_documentos_by_orden_venta(
    IN orden_venta_id_in INT
)
BEGIN
    SELECT * FROM DOCUMENTO_DE_VENTAS
    WHERE orden_de_venta_id = orden_venta_id_in;
END;
//
DELIMITER ;

/* USER */
/* PROCEDURES LOG */

DELIMITER //
CREATE PROCEDURE add_log(
    OUT generated_id INT,
    IN accion_in VARCHAR(200),
    IN fecha_in DATE,
    IN usuario_id_in INT
)
BEGIN
    INSERT INTO LOG (accion, fecha, usuario_id)
    VALUES (accion_in, fecha_in, usuario_id_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_log(
    IN log_id INT,
    IN nueva_accion VARCHAR(200),
    IN nueva_fecha DATE,
    IN nuevo_usuario_id INT
)
BEGIN
    UPDATE LOG
    SET accion = nueva_accion,
        fecha = nueva_fecha,
        usuario_id = nuevo_usuario_id
    WHERE id = log_id;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_log(
    IN id_in INT
)
BEGIN
    DELETE FROM LOG WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_log(
    IN id_in INT
)
BEGIN
    SELECT * FROM LOG WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_logs()
BEGIN
    SELECT * FROM LOG;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_logs_by_usuario(
    IN usuario_id_in INT
)
BEGIN
    SELECT * 
    FROM LOG 
    WHERE usuario_id = usuario_id_in
    ORDER BY fecha DESC;
END;
//
DELIMITER ;

/* PROCEDURES USUARIO */

DELIMITER //
CREATE PROCEDURE add_usuario(
    OUT generated_id INT,
    IN username_in VARCHAR(20),
    IN nombre_in VARCHAR(150),
    IN telefono_in VARCHAR(15),
    IN correo_in VARCHAR(100),
    IN direccion_in VARCHAR(100),
    IN password_in VARCHAR(255)
)
BEGIN
    INSERT INTO USUARIO (
        username, 
        nombre, 
        telefono, 
        correo, 
        direccion, 
        password
    ) VALUES (
        username_in, 
        nombre_in, 
        telefono_in, 
        correo_in, 
        direccion_in, 
        password_in
    );
    
    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_usuario(
    IN id_in INT,
    IN username_in VARCHAR(20),
    IN nombre_in VARCHAR(150),
    IN telefono_in VARCHAR(15),
    IN correo_in VARCHAR(100),
    IN direccion_in VARCHAR(100),
    IN password_in VARCHAR(255)
)
BEGIN
    UPDATE USUARIO
    SET 
        username = username_in,
        nombre = nombre_in,
        telefono = telefono_in,
        correo = correo_in,
        direccion = direccion_in,
        password = password_in
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_usuario(
    IN id_in INT)
BEGIN
    DELETE FROM USUARIO WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_usuario(
    IN id_in INT)
BEGIN
    SELECT 
        id,
        username,
        nombre,
        telefono,
        correo,
        direccion,
        password,
        created_at,
        updated_at
    FROM USUARIO 
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_usuarios()
BEGIN
    SELECT 
        id,
        username,
        nombre,
        telefono,
        correo,
        direccion,
        password,
        created_at,
        updated_at
    FROM USUARIO;
END;
//
DELIMITER ;

/* PROCEDURES ADMIN */

DELIMITER //
CREATE PROCEDURE add_admin(
    IN usuario_id_in INT,
    IN fecha_ingreso_in DATE
)
BEGIN
    INSERT INTO ADMIN (usuario_id, fecha_ingreso)
    VALUES (usuario_id_in, fecha_ingreso_in);
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_admin(
    IN usuario_id_in INT,
    IN fecha_ingreso_in DATE
)
BEGIN
    UPDATE ADMIN
    SET fecha_ingreso = fecha_ingreso_in
    WHERE usuario_id = usuario_id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_admin(
    IN usuario_id_in INT
)
BEGIN
    SELECT 
        a.usuario_id,
        a.fecha_ingreso,
        u.id,
        u.username,
        u.nombre,
        u.telefono,
        u.correo,
        u.direccion,
        u.created_at,
        u.updated_at
    FROM ADMIN a
    JOIN USUARIO u ON a.usuario_id = u.id
    WHERE a.usuario_id = usuario_id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_admins()
BEGIN
    SELECT 
        a.usuario_id,
        a.fecha_ingreso,
        u.id,
        u.username,
        u.nombre,
        u.telefono,
        u.correo,
        u.direccion,
        u.created_at,
        u.updated_at
    FROM ADMIN a
    JOIN USUARIO u ON a.usuario_id = u.id;
END;
//
DELIMITER ;

/* PROCEDURES CLIENTE */

DELIMITER //
CREATE PROCEDURE add_cliente(
    IN usuario_id_in INT,
    IN direccion_envio_in VARCHAR(100)
)
BEGIN
    INSERT INTO CLIENTE (usuario_id, direccion_envio)
    VALUES (usuario_id_in, direccion_envio_in);
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_cliente(
    IN usuario_id_in INT,
    IN direccion_envio_in VARCHAR(100)
)
BEGIN
    UPDATE CLIENTE
    SET direccion_envio = direccion_envio_in
    WHERE usuario_id = usuario_id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_cliente(
    IN usuario_id_in INT)
BEGIN
    SELECT 
        c.usuario_id,
        c.direccion_envio,
        u.id,
        u.username,
        u.nombre,
        u.telefono,
        u.correo,
        u.direccion,
        u.created_at,
        u.updated_at
    FROM CLIENTE c
    JOIN USUARIO u ON c.usuario_id = u.id
    WHERE c.usuario_id = usuario_id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_clientes()
BEGIN
    SELECT 
        c.usuario_id,
        c.direccion_envio,
        u.id,
        u.username,
        u.nombre,
        u.telefono,
        u.correo,
        u.direccion,
        u.created_at,
        u.updated_at
    FROM CLIENTE c
    JOIN USUARIO u ON c.usuario_id = u.id;
END;
//
DELIMITER ;

/* FINANCIAL */
/* PROCEDURES IMPUESTO */

DELIMITER //
CREATE PROCEDURE add_impuesto(
    OUT generated_id INT,
    IN nombre_in VARCHAR(20),
    IN abreviacion_in VARCHAR(10),
    IN tipo_in ENUM('COMPRA', 'VENTA')
)
BEGIN
    INSERT INTO IMPUESTO (nombre, abreviacion, tipo)
    VALUES (nombre_in, abreviacion_in, tipo_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_impuesto(
    IN id_in INT,
    IN nombre_in VARCHAR(20),
    IN abreviacion_in VARCHAR(10),
    IN tipo_in ENUM('COMPRA', 'VENTA')
)
BEGIN
    UPDATE IMPUESTO
    SET nombre = nombre_in,
        abreviacion = abreviacion_in,
        tipo = tipo_in
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_impuesto(
    IN id_in INT
)
BEGIN
    DELETE FROM IMPUESTO
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_impuesto(
    IN id_in INT
)
BEGIN
    SELECT * FROM IMPUESTO
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_impuesto()
BEGIN
    SELECT * FROM IMPUESTO;
END;
//
DELIMITER ;

/* PROCEDURES METODO_DE_PAGO */

DELIMITER //
CREATE PROCEDURE add_metodo_pago(
    OUT generated_id INT,
    IN nombre_in VARCHAR(30),
    IN descripcion_in VARCHAR(30),
    IN estado_in ENUM('ACTIVO', 'INACTIVO')
)
BEGIN
    INSERT INTO METODO_DE_PAGO (nombre, descripcion, estado)
    VALUES (nombre_in, descripcion_in, estado_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_metodo_pago(
    IN id_in INT,
    IN nombre_in VARCHAR(30),
    IN descripcion_in VARCHAR(30),
    IN estado_in ENUM('ACTIVO', 'INACTIVO')
)
BEGIN
    UPDATE METODO_DE_PAGO
    SET nombre = nombre_in,
        descripcion = descripcion_in,
        estado = estado_in
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_metodo_pago(
    IN id_in INT
)
BEGIN
    DELETE FROM METODO_DE_PAGO
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_metodo_pago(
    IN id_in INT
)
BEGIN
    SELECT * FROM METODO_DE_PAGO
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_metodo_pago()
BEGIN
    SELECT * FROM METODO_DE_PAGO;
END;
//
DELIMITER ;

/* PROCEDURES MONEDA */

DELIMITER //
CREATE PROCEDURE add_moneda(
    OUT generated_id INT,
    IN codigo_in VARCHAR(3),
    IN nombre_in VARCHAR(20)
)
BEGIN
    INSERT INTO MONEDA (codigo, nombre)
    VALUES (codigo_in, nombre_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_moneda(
    IN id_in INT,
    IN codigo_in VARCHAR(3),
    IN nombre_in VARCHAR(20)
)
BEGIN
    UPDATE MONEDA
    SET codigo = codigo_in,
        nombre = nombre_in
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_moneda(
    IN id_in INT
)
BEGIN
    DELETE FROM MONEDA
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_moneda(
    IN id_in INT
)
BEGIN
    SELECT * FROM MONEDA
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_moneda()
BEGIN
    SELECT * FROM MONEDA;
END;
//
DELIMITER ;

/* PROCEDURES PAGO */

DELIMITER //
CREATE PROCEDURE add_pago(
    OUT generated_id INT,
    IN monto_in DECIMAL(10,2),
    IN fecha_pago_in DATE,
    IN estado_in ENUM('P', 'F', 'E'),
    IN referencia_in VARCHAR(50),
    IN doc_venta_num INT,
    IN doc_compra_num INT,
    IN metodo_pago_id INT,
    IN moneda_id INT,
    IN periodo_id INT
)
BEGIN
    INSERT INTO PAGO (
        monto, fecha_pago, estado, referencia,
        documento_de_ventas_numero, documento_de_compras_numero,
        metodo_de_pago_id, moneda_periodo_moneda_id, moneda_periodo_periodo_id
    )
    VALUES (
        monto_in, fecha_pago_in, estado_in, referencia_in,
        doc_venta_num, doc_compra_num,
        metodo_pago_id, moneda_id, periodo_id
    );

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_pago(
    IN id_in INT,
    IN monto_in DECIMAL(10,2),
    IN fecha_pago_in DATE,
    IN estado_in ENUM('P', 'F', 'E'),
    IN referencia_in VARCHAR(50),
    IN doc_venta_num INT,
    IN doc_compra_num INT,
    IN metodo_pago_id INT,
    IN moneda_id INT,
    IN periodo_id INT
)
BEGIN
    UPDATE PAGO
    SET monto = monto_in,
        fecha_pago = fecha_pago_in,
        estado = estado_in,
        referencia = referencia_in,
        documento_de_ventas_numero = doc_venta_num,
        documento_de_compras_numero = doc_compra_num,
        metodo_de_pago_id = metodo_pago_id,
        moneda_periodo_moneda_id = moneda_id,
        moneda_periodo_periodo_id = periodo_id
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_pago(
    IN id_in INT
)
BEGIN
    DELETE FROM PAGO WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_pago(
    IN id_in INT
)
BEGIN
    SELECT * FROM PAGO WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_pago()
BEGIN
    SELECT * FROM PAGO;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_pagos_by_documento_venta(
    IN numero_in INT
)
BEGIN
    SELECT * FROM PAGO WHERE documento_de_ventas_numero = numero_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_pago_by_documento_compra(
    IN numero_in INT
)
BEGIN
    SELECT * FROM PAGO WHERE documento_de_compras_numero = numero_in;
END;
//
DELIMITER ;

/* CONFIGURATION */
/* PROCEDURES IMPUESTO_PERIODO */

DELIMITER //
CREATE PROCEDURE add_impuesto_periodo(
    OUT generated_id INT,
    IN periodo_id_in INT,
    IN impuesto_id_in INT,
    IN tasa_in DECIMAL(5,4),
    IN estado_in ENUM('ACTIVO', 'INACTIVO')
)
BEGIN
    INSERT INTO IMPUESTO_PERIODO (
        periodo_id, impuesto_id, tasa, estado
    ) VALUES (
        periodo_id_in, impuesto_id_in, tasa_in, estado_in
    );

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_impuesto_periodo(
    IN periodo_id_in INT,
    IN impuesto_id_in INT,
    IN tasa_in DECIMAL(5,4),
    IN estado_in ENUM('ACTIVO', 'INACTIVO')
)
BEGIN
    UPDATE IMPUESTO_PERIODO
    SET tasa = tasa_in,
        estado = estado_in
    WHERE periodo_id = periodo_id_in AND impuesto_id = impuesto_id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_impuesto_periodo(
    IN periodo_id_in INT,
    IN impuesto_id_in INT
)
BEGIN
    DELETE FROM IMPUESTO_PERIODO
    WHERE periodo_id = periodo_id_in AND impuesto_id = impuesto_id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_impuesto_periodo(
    IN periodo_id_in INT,
    IN impuesto_id_in INT
)
BEGIN
    SELECT * FROM IMPUESTO_PERIODO
    WHERE periodo_id = periodo_id_in AND impuesto_id = impuesto_id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_impuesto_periodo()
BEGIN
    SELECT * FROM IMPUESTO_PERIODO;
END;
//
DELIMITER ;

/* PROCEDURES MONEDA_PERIODO */

DELIMITER //
CREATE PROCEDURE add_moneda_periodo(
    OUT generated_id INT,
    IN moneda_id_in INT,
    IN periodo_id_in INT,
    IN tipoCambio_in ENUM('COMPRA', 'VENTA'),
    IN estado_in ENUM('ACTIVO', 'INACTIVO'),
    IN valor_in DECIMAL(10,2)
)
BEGIN
    INSERT INTO MONEDA_PERIODO (
        moneda_id, periodo_id, tipoCambio, estado, valor
    ) VALUES (
        moneda_id_in, periodo_id_in, tipoCambio_in, estado_in, valor_in
    );

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_moneda_periodo(
    IN moneda_id_in INT,
    IN periodo_id_in INT,
    IN tipoCambio_in ENUM('COMPRA', 'VENTA'),
    IN estado_in ENUM('ACTIVO', 'INACTIVO'),
    IN valor_in DECIMAL(10,2)
)
BEGIN
    UPDATE MONEDA_PERIODO
    SET tipoCambio = tipoCambio_in,
        estado = estado_in,
        valor = valor_in
    WHERE moneda_id = moneda_id_in AND periodo_id = periodo_id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_moneda_periodo(
    IN moneda_id_in INT,
    IN periodo_id_in INT
)
BEGIN
    DELETE FROM MONEDA_PERIODO
    WHERE moneda_id = moneda_id_in AND periodo_id = periodo_id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_moneda_periodo(
    IN moneda_id_in INT,
    IN periodo_id_in INT
)
BEGIN
    SELECT * FROM MONEDA_PERIODO
    WHERE moneda_id = moneda_id_in AND periodo_id = periodo_id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_moneda_periodo()
BEGIN
    SELECT * FROM MONEDA_PERIODO;
END;
//
DELIMITER ;

/* PROCEDURES PERIODO */

DELIMITER //
CREATE PROCEDURE add_periodo(
    OUT generated_id INT,
    IN fecha_inicio_in DATE,
    IN fecha_fin_in DATE
)
BEGIN
    INSERT INTO PERIODO (fecha_inicio, fecha_fin)
    VALUES (fecha_inicio_in, fecha_fin_in);

    SET generated_id = LAST_INSERT_ID();
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_periodo(
    IN id_in INT,
    IN fecha_inicio_in DATE,
    IN fecha_fin_in DATE
)
BEGIN
    UPDATE PERIODO
    SET fecha_inicio = fecha_inicio_in,
        fecha_fin = fecha_fin_in
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_periodo(
    IN id_in INT
)
BEGIN
    DELETE FROM PERIODO
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_periodo(
    IN id_in INT
)
BEGIN
    SELECT * FROM PERIODO
    WHERE id = id_in;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_periodo()
BEGIN
    SELECT * FROM PERIODO;
END;
//
DELIMITER ;