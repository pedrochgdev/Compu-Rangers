-- Generado por Oracle SQL Developer Data Modeler 24.3.1.347.1153
--   en:        2025-04-06 22:17:55 PET
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE categoria (
    id     NUMBER NOT NULL,
    nombre VARCHAR2(30 BYTE) NOT NULL
);

COMMENT ON COLUMN categoria.id IS
    'Identificador de la categoria.';

COMMENT ON COLUMN categoria.nombre IS
    'NOMBRE DE LA CATEGORIA';

ALTER TABLE categoria ADD CONSTRAINT categoria_pk PRIMARY KEY ( id );

CREATE TABLE cliente (
    id_cliente          NUMBER NOT NULL,
    nombre              VARCHAR2(150 BYTE) NOT NULL,
    documento_identidad VARCHAR2(12 BYTE) NOT NULL,
    telefono            VARCHAR2(15 BYTE) NOT NULL,
    correo_electronico  VARCHAR2(70 BYTE) NOT NULL,
    id_orden_de_venta   NUMBER NOT NULL
);

COMMENT ON COLUMN cliente.id_cliente IS
    'IDENTIFICADOR DEL CLIENTE';

COMMENT ON COLUMN cliente.nombre IS
    'NOMBRE COMPLETO DEL CLIENTE';

COMMENT ON COLUMN cliente.documento_identidad IS
    'DOCUMENTO DE IDENTIDAD DEL CLIENTE';

COMMENT ON COLUMN cliente.telefono IS
    'NUMERO TELEFONICO DEL CLIENTE';

COMMENT ON COLUMN cliente.correo_electronico IS
    'CORREO ELECTRONICO DEL CLIENTE';

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( id_cliente );

CREATE TABLE detalle_devolucion (
    id_1                NUMBER NOT NULL,
    cantidad            NUMBER NOT NULL,
    id_detalle_venta    NUMBER NOT NULL,
    id_orden_devolucion NUMBER NOT NULL
);

COMMENT ON COLUMN detalle_devolucion.id_1 IS
    'IDENTIFICADOR DEL DETALLE DE DEVOLUCION';

COMMENT ON COLUMN detalle_devolucion.cantidad IS
    'CANTIDAD DE PRODUCTOS DEVUELTOS';

ALTER TABLE detalle_devolucion ADD CONSTRAINT detalle_devolucion_pk PRIMARY KEY ( id_1 );

CREATE TABLE detalle_lote (
    id_detlot     NUMBER NOT NULL,
    cantidad      NUMBER NOT NULL,
    precio_compra NUMBER(10, 2) NOT NULL,
    id_lote       NUMBER NOT NULL,
    id_pt         NUMBER NOT NULL
);

COMMENT ON COLUMN detalle_lote.id_detlot IS
    'IDENTIFICADOR DEL DETALLE LOTE';

COMMENT ON COLUMN detalle_lote.cantidad IS
    'CANTIDAD DEL LOTE';

COMMENT ON COLUMN detalle_lote.precio_compra IS
    'PRECIO DE COMPRA DEL LOTE';

ALTER TABLE detalle_lote ADD CONSTRAINT detalle_lote_pk PRIMARY KEY ( id_detlot );

CREATE TABLE detalle_venta (
    id                NUMBER NOT NULL,
    cantidad          NUMBER NOT NULL,
    precio_producto   NUMBER(8, 2) NOT NULL,
    cantidad_devuelta NUMBER,
    id_pt             NUMBER NOT NULL,
    id_orden_venta    NUMBER NOT NULL
);

COMMENT ON COLUMN detalle_venta.id IS
    'IDENTIFICADOR DEL DETALLE VENTA';

COMMENT ON COLUMN detalle_venta.cantidad IS
    'CANTIDAD DE PRODUCTOS';

COMMENT ON COLUMN detalle_venta.precio_producto IS
    'PRECIO UNTIARIO DE PRODUCTO';

COMMENT ON COLUMN detalle_venta.cantidad_devuelta IS
    'CANTIDAD DEVUELTA DEL PRODUCTO';

ALTER TABLE detalle_venta ADD CONSTRAINT detalle_venta_pk PRIMARY KEY ( id );

CREATE TABLE documento_compras (
    numero       NUMBER NOT NULL,
    subtotal     NUMBER(10, 2) NOT NULL,
    impuestos    NUMBER(10, 2) NOT NULL,
    total        NUMBER(10, 2) NOT NULL,
    id_proveedor NUMBER NOT NULL
);

COMMENT ON COLUMN documento_compras.numero IS
    'NUMERO DEL DOCUMENTO DE COMPRA';

COMMENT ON COLUMN documento_compras.subtotal IS
    'SUBTOTAL DEL DOCUMENTO DE COMPRA';

COMMENT ON COLUMN documento_compras.impuestos IS
    'IMPUESTOS DEL DOCUMENTO DE COMPRA';

COMMENT ON COLUMN documento_compras.total IS
    'TOTAL DEL DOCUMENTO DE COMPRAS';

COMMENT ON COLUMN documento_compras.id_proveedor IS
    'IDENTIFICADOR DEL PROVEEDOR';

ALTER TABLE documento_compras ADD CONSTRAINT documento_compras_pk PRIMARY KEY ( numero );

CREATE TABLE documento_de_ventas (
    numero            NUMBER NOT NULL,
    subtotal          NUMBER(10, 2) NOT NULL,
    impuestos         NUMBER(8, 2) NOT NULL,
    total             NUMBER(10, 2) NOT NULL,
    total_pagado      NUMBER(10, 2) NOT NULL,
    total_pendiente   NUMBER(10, 2) NOT NULL,
    id_orden_de_venta NUMBER NOT NULL
);

COMMENT ON COLUMN documento_de_ventas.numero IS
    'NUMERO DE DOCUMENTO DE VENTAS';

COMMENT ON COLUMN documento_de_ventas.subtotal IS
    'SUBTOTAL DE LA VENTA';

COMMENT ON COLUMN documento_de_ventas.impuestos IS
    'IMPUESTOS DE LA VENTA';

COMMENT ON COLUMN documento_de_ventas.total IS
    'TOTAL DE LA VENTA';

COMMENT ON COLUMN documento_de_ventas.total_pagado IS
    'TOTAL PAGADO DE LA VENTA';

COMMENT ON COLUMN documento_de_ventas.total_pendiente IS
    'TOTAL PENDIENTE DE LA VENTA';

CREATE UNIQUE INDEX documento_de_ventas__idx ON
    documento_de_ventas (
        id_orden_de_venta
    ASC );

ALTER TABLE documento_de_ventas ADD CONSTRAINT documento_de_ventas_pk PRIMARY KEY ( numero );

CREATE TABLE impuesto (
    id          NUMBER NOT NULL,
    nombre      VARCHAR2(20 BYTE) NOT NULL,
    abreviacion VARCHAR2(10 BYTE) NOT NULL,
    tipo        CHAR(1 BYTE) NOT NULL
);

COMMENT ON COLUMN impuesto.id IS
    'IDENTIFICADOR DEL IMPUESTO';

COMMENT ON COLUMN impuesto.nombre IS
    'NOMBRE DEL IMPUESTO';

COMMENT ON COLUMN impuesto.abreviacion IS
    'ABREVIACION DEL IMPUESTO';

COMMENT ON COLUMN impuesto.tipo IS
    'TIPO DEL IMPUESTO';

ALTER TABLE impuesto ADD CONSTRAINT impuesto_pk PRIMARY KEY ( id );

CREATE TABLE impuesto_documento (
    id                 NUMBER NOT NULL,
    monto              NUMBER(10, 2) NOT NULL,
    numero_doc_ventas  NUMBER,
    numero_doc_compras NUMBER,
    id_periodo         NUMBER NOT NULL,
    id_impuesto        NUMBER NOT NULL
);

COMMENT ON COLUMN impuesto_documento.id IS
    'IDENTIFICADOR DEL IMPUESTO DOCUMENTO';

COMMENT ON COLUMN impuesto_documento.monto IS
    'MONTO DEL DOCUMENTO IMPUESTO';

ALTER TABLE impuesto_documento ADD CONSTRAINT impuesto_documento_pk PRIMARY KEY ( id );

CREATE TABLE impuesto_periodo (
    periodo_id  NUMBER NOT NULL,
    id_impuesto NUMBER NOT NULL,
    tasa        NUMBER(5, 4) NOT NULL,
    estado      CHAR(1 BYTE) NOT NULL
);

COMMENT ON COLUMN impuesto_periodo.tasa IS
    'TASA DEL IMPUESTO EN EL PERIODO';

COMMENT ON COLUMN impuesto_periodo.estado IS
    'ESTADO DEL IMPUESTO EN EL PERIODO';

ALTER TABLE impuesto_periodo ADD CONSTRAINT impuesto_periodo_pk PRIMARY KEY ( periodo_id,
                                                                              id_impuesto );

CREATE TABLE inventario (
    id_inventario       NUMBER NOT NULL,
    cantidad_disponible NUMBER NOT NULL,
    cantidad_reservada  NUMBER NOT NULL,
    id_lote             NUMBER NOT NULL,
    id_pt               NUMBER NOT NULL
);

COMMENT ON COLUMN inventario.id_inventario IS
    'IDENTIFICADOR DEL INVENTARIO';

COMMENT ON COLUMN inventario.cantidad_disponible IS
    'CANTIDAD DISPONIBLE DEL INVENTARIO';

COMMENT ON COLUMN inventario.cantidad_reservada IS
    'CANTIDAD RESERVADA DEL INVENTARIO';

ALTER TABLE inventario ADD CONSTRAINT inventario_pk PRIMARY KEY ( id_inventario );

CREATE TABLE log (
    id         NUMBER NOT NULL,
    accion     VARCHAR2(200 BYTE) NOT NULL,
    fecha      DATE NOT NULL,
    id_usuario NUMBER NOT NULL
);

COMMENT ON COLUMN log.id IS
    'IDENTIFICADOR DEL LOGGIN DEL USUARIO';

COMMENT ON COLUMN log.accion IS
    'ACCION DEL LOGGIN';

COMMENT ON COLUMN log.fecha IS
    'FECHA DEL LOGGIN';

ALTER TABLE log ADD CONSTRAINT log_pk PRIMARY KEY ( id );

CREATE TABLE lote (
    id             NUMBER NOT NULL,
    fecha_creacion DATE NOT NULL,
    estado         CHAR(1 BYTE) NOT NULL,
    n_doc_compras  NUMBER NOT NULL
);

COMMENT ON COLUMN lote.id IS
    'IDENTIFICADOR DEL LOTE';

COMMENT ON COLUMN lote.fecha_creacion IS
    'FECHA DE CREACION DEL LOTE';

COMMENT ON COLUMN lote.estado IS
    'ESTADO DEL LOTE';

ALTER TABLE lote ADD CONSTRAINT lote_pk PRIMARY KEY ( id );

CREATE TABLE metado_de_pago (
    id          NUMBER NOT NULL,
    nombre      VARCHAR2(30 BYTE) NOT NULL,
    descripcion VARCHAR2(30 BYTE) NOT NULL,
    estado      CHAR(1 BYTE) NOT NULL
);

COMMENT ON COLUMN metado_de_pago.id IS
    'IDENTIFICADOR DEL METODO DE PAGO';

COMMENT ON COLUMN metado_de_pago.nombre IS
    'NOMBRE DEL METODO DE PAGO
';

COMMENT ON COLUMN metado_de_pago.descripcion IS
    'DESCRIPCION DEL METADO DE PAGO';

COMMENT ON COLUMN metado_de_pago.estado IS
    'ESTADO DEL METODO DE PAGO';

ALTER TABLE metado_de_pago ADD CONSTRAINT metado_de_pago_pk PRIMARY KEY ( id );

CREATE TABLE moneda (
    id     NUMBER NOT NULL,
    codigo VARCHAR2(3 BYTE) NOT NULL,
    nombre VARCHAR2(20 BYTE) NOT NULL
);

COMMENT ON COLUMN moneda.id IS
    'IDENTIFICADOR DE LA MONEDA';

COMMENT ON COLUMN moneda.codigo IS
    'CODIGO DE LA MONEDA';

COMMENT ON COLUMN moneda.nombre IS
    'NOMBRE DE LA MONEDA';

ALTER TABLE moneda ADD CONSTRAINT moneda_pk PRIMARY KEY ( id );

CREATE TABLE orden_de_venta (
    id         NUMBER NOT NULL,
    estado     CHAR(1 BYTE) NOT NULL,
    fecha      DATE NOT NULL,
    id_usuario NUMBER NOT NULL
);

COMMENT ON COLUMN orden_de_venta.id IS
    'IDENTIFICADOR DEL ORDEN DE VENTA';

COMMENT ON COLUMN orden_de_venta.estado IS
    'ESTADO DE LA ORDEN DE VENTA';

COMMENT ON COLUMN orden_de_venta.fecha IS
    'FECHA DE REGISTRO DE  LA ORDEN DE VENTA';

ALTER TABLE orden_de_venta ADD CONSTRAINT orden_de_venta_pk PRIMARY KEY ( id );

CREATE TABLE orden_devolucion (
    id                NUMBER NOT NULL,
    motivo            VARCHAR2(200 BYTE) NOT NULL,
    fecha_registro    DATE NOT NULL,
    tipo_devolucion   CHAR(1 BYTE) NOT NULL,
    numero_doc_ventas NUMBER NOT NULL
);

COMMENT ON COLUMN orden_devolucion.id IS
    'IDENTIFICADOR DEL ORDEN DE DEVOLUCION';

COMMENT ON COLUMN orden_devolucion.motivo IS
    'MOTIVO DE LA DEVOLUCION
';

COMMENT ON COLUMN orden_devolucion.fecha_registro IS
    'FECHA REGISTRADA DE LA DEVOLUCION';

COMMENT ON COLUMN orden_devolucion.tipo_devolucion IS
    'TIPO DE DEVOLUCION ';

ALTER TABLE orden_devolucion ADD CONSTRAINT orden_devolucion_pk PRIMARY KEY ( id );

CREATE TABLE pago (
    id                 NUMBER NOT NULL,
    monto              NUMBER(10, 2) NOT NULL,
    fecha_pago         DATE NOT NULL,
    estado             CHAR(1 BYTE) NOT NULL,
    referencia         VARCHAR2(50 BYTE),
    numero_doc_ventas  NUMBER NOT NULL,
    id_metodo_pago     NUMBER NOT NULL,
    id_moneda          NUMBER NOT NULL,
    id_tipo_de_cambio_ NUMBER NOT NULL,
    id_periodo         NUMBER NOT NULL
);

COMMENT ON COLUMN pago.id IS
    'IDENTIFICADOR DEL PAGO ';

COMMENT ON COLUMN pago.monto IS
    'MONTO DE PAGO';

COMMENT ON COLUMN pago.fecha_pago IS
    'FECHA EN LA QUE SE EFECTUO EL PAGO';

COMMENT ON COLUMN pago.estado IS
    'ESTADO EN EL QUE SE ENCUENTRA EL PAGO';

COMMENT ON COLUMN pago.referencia IS
    'REFERENCIA DE PAGO';

ALTER TABLE pago ADD CONSTRAINT pago_pk PRIMARY KEY ( id );

CREATE TABLE periodo (
    id           NUMBER NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin    DATE
);

COMMENT ON COLUMN periodo.id IS
    'IDENTIFICADOR DEL PERIODO';

COMMENT ON COLUMN periodo.fecha_inicio IS
    'FECHA DE INICIO DEL PERIODO';

COMMENT ON COLUMN periodo.fecha_fin IS
    'FECHA DE FIN DEL PERIODO';

ALTER TABLE periodo ADD CONSTRAINT periodo_pk PRIMARY KEY ( id );

CREATE TABLE producto (
    id           NUMBER NOT NULL,
    descripcion  VARCHAR2(100 BYTE) NOT NULL,
    sku          VARCHAR2(20 BYTE) NOT NULL,
    precio_venta NUMBER(8, 2) NOT NULL,
    id_categoria NUMBER NOT NULL
);

COMMENT ON COLUMN producto.id IS
    'IDENTIFICADOR DEL PRODUCTO';

COMMENT ON COLUMN producto.descripcion IS
    'DESCRIPCION DEL PRODUCTO';

COMMENT ON COLUMN producto.sku IS
    'STOCK KEEPING UNIT("UNIDAD DE MANTENIMIENTO DE STOCK")';

COMMENT ON COLUMN producto.precio_venta IS
    'PRECIO DE VENTA DEL PRODUCTO';

ALTER TABLE producto ADD CONSTRAINT producto_pk PRIMARY KEY ( id );

CREATE TABLE producto_talla (
    id_pt       NUMBER NOT NULL,
    id_producto NUMBER NOT NULL,
    id_talla    NUMBER
);

COMMENT ON COLUMN producto_talla.id_pt IS
    'IDENTIFICADOR DEL PRODUCTO TALLA';

ALTER TABLE producto_talla ADD CONSTRAINT producto_talla_pk PRIMARY KEY ( id_pt );

CREATE TABLE proveedor (
    id           NUMBER NOT NULL,
    razon_social VARCHAR2(100 BYTE) NOT NULL
);

COMMENT ON COLUMN proveedor.id IS
    'IDENTIFICADOR DEL PROOVEDOR';

COMMENT ON COLUMN proveedor.razon_social IS
    'RAZON SOCIAL DEL PROOVEDOR';

ALTER TABLE proveedor ADD CONSTRAINT proveedor_pk PRIMARY KEY ( id );

CREATE TABLE reserva (
    id                NUMBER NOT NULL,
    monto_separacion  NUMBER(10, 2) NOT NULL,
    estado            CHAR(1 BYTE) NOT NULL,
    numero_doc_ventas NUMBER NOT NULL
);

COMMENT ON COLUMN reserva.id IS
    'IDENTIFICADOR DE LA RESERVA';

COMMENT ON COLUMN reserva.monto_separacion IS
    'MONTO DE SEPARACION DE RESERVA';

COMMENT ON COLUMN reserva.estado IS
    'ESTADO DE LA RESERVA';

CREATE UNIQUE INDEX reserva__idx ON
    reserva (
        numero_doc_ventas
    ASC );

ALTER TABLE reserva ADD CONSTRAINT reserva_pk PRIMARY KEY ( id );

CREATE TABLE talla (
    id          NUMBER NOT NULL,
    descripcion VARCHAR2(20 BYTE) NOT NULL,
    abreviatura VARCHAR2(10 BYTE) NOT NULL
);

COMMENT ON COLUMN talla.id IS
    'IDENTIFICADOR DE TALLA';

COMMENT ON COLUMN talla.descripcion IS
    'DESCRIPCION DE TALLA';

COMMENT ON COLUMN talla.abreviatura IS
    'ABREVIATURA DE TALLA
';

ALTER TABLE talla ADD CONSTRAINT talla_pk PRIMARY KEY ( id );

CREATE TABLE tipo_cambio_periodo (
    id_moneda         NUMBER NOT NULL,
    id_tipo_de_cambio NUMBER NOT NULL,
    id_periodo        NUMBER NOT NULL,
    estado            CHAR(1 BYTE),
    valor             NUMBER(10, 2) NOT NULL
);

COMMENT ON COLUMN tipo_cambio_periodo.estado IS
    'ESTADO DEL TIPO DE CAMBIO POR PERIODO';

COMMENT ON COLUMN tipo_cambio_periodo.valor IS
    'VALOR DEL TIPO DE CAMBIO POR PERIODO';

ALTER TABLE tipo_cambio_periodo
    ADD CONSTRAINT tipo_cambio_periodo_pk PRIMARY KEY ( id_moneda,
                                                        id_tipo_de_cambio,
                                                        id_periodo );

CREATE TABLE tipo_de_cambio (
    id   NUMBER NOT NULL,
    tipo CHAR(1 BYTE) NOT NULL
);

COMMENT ON COLUMN tipo_de_cambio.id IS
    'IDENTIFICADOR DEL TIPO DE CAMBIO';

COMMENT ON COLUMN tipo_de_cambio.tipo IS
    'TIPO DE CAMBIO';

ALTER TABLE tipo_de_cambio ADD CONSTRAINT tipo_de_cambio_pk PRIMARY KEY ( id );

CREATE TABLE usuario (
    id       NUMBER NOT NULL,
    username VARCHAR2(20 BYTE) NOT NULL,
    nombre   VARCHAR2(150 BYTE) NOT NULL,
    rol      VARCHAR2(20 BYTE) NOT NULL,
    correo   VARCHAR2(100 BYTE) NOT NULL,
    password VARCHAR2(40 BYTE) NOT NULL
);

COMMENT ON COLUMN usuario.id IS
    'IDENTIFICADOR DEL USUARIO';

COMMENT ON COLUMN usuario.username IS
    'USERNAME DEL USUARIO';

COMMENT ON COLUMN usuario.nombre IS
    'NOMBRE DEL USUARIO';

COMMENT ON COLUMN usuario.rol IS
    'ROL DEL USUARIO';

COMMENT ON COLUMN usuario.correo IS
    'CORREO DEL USUARIO';

COMMENT ON COLUMN usuario.password IS
    'CONTRASENA DEL USUARIO';

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id );

ALTER TABLE cliente
    ADD CONSTRAINT cliente_ordven_fk FOREIGN KEY ( id_orden_de_venta )
        REFERENCES orden_de_venta ( id );

ALTER TABLE detalle_devolucion
    ADD CONSTRAINT detdev_detven_fk FOREIGN KEY ( id_detalle_venta )
        REFERENCES detalle_venta ( id );

ALTER TABLE detalle_devolucion
    ADD CONSTRAINT detdev_orddev_fk FOREIGN KEY ( id_orden_devolucion )
        REFERENCES orden_devolucion ( id );

ALTER TABLE detalle_lote
    ADD CONSTRAINT detlot_lote_fk FOREIGN KEY ( id_lote )
        REFERENCES lote ( id );

ALTER TABLE detalle_lote
    ADD CONSTRAINT detlot_prodtalla_fk FOREIGN KEY ( id_pt )
        REFERENCES producto_talla ( id_pt );

ALTER TABLE detalle_venta
    ADD CONSTRAINT detven_ordven_fk FOREIGN KEY ( id_orden_venta )
        REFERENCES orden_de_venta ( id );

ALTER TABLE detalle_venta
    ADD CONSTRAINT detven_prodtalla_fk FOREIGN KEY ( id_pt )
        REFERENCES producto_talla ( id_pt );

ALTER TABLE documento_compras
    ADD CONSTRAINT doccom_proveedor_fk FOREIGN KEY ( id_proveedor )
        REFERENCES proveedor ( id );

ALTER TABLE documento_de_ventas
    ADD CONSTRAINT docven_ordven_fk FOREIGN KEY ( id_orden_de_venta )
        REFERENCES orden_de_venta ( id );

ALTER TABLE impuesto_documento
    ADD CONSTRAINT impdoc_doccom_fk FOREIGN KEY ( numero_doc_compras )
        REFERENCES documento_compras ( numero );

ALTER TABLE impuesto_documento
    ADD CONSTRAINT impdoc_docven_fk FOREIGN KEY ( numero_doc_ventas )
        REFERENCES documento_de_ventas ( numero );

ALTER TABLE impuesto_documento
    ADD CONSTRAINT impdoc_impper_fk
        FOREIGN KEY ( id_periodo,
                      id_impuesto )
            REFERENCES impuesto_periodo ( periodo_id,
                                          id_impuesto );

ALTER TABLE impuesto_periodo
    ADD CONSTRAINT impper_impuesto_fk FOREIGN KEY ( id_impuesto )
        REFERENCES impuesto ( id );

ALTER TABLE impuesto_periodo
    ADD CONSTRAINT impper_periodo_fk FOREIGN KEY ( periodo_id )
        REFERENCES periodo ( id );

ALTER TABLE inventario
    ADD CONSTRAINT inventario_lote_fk FOREIGN KEY ( id_lote )
        REFERENCES lote ( id );

ALTER TABLE inventario
    ADD CONSTRAINT inventario_prodtalla_fk FOREIGN KEY ( id_pt )
        REFERENCES producto_talla ( id_pt );

ALTER TABLE log
    ADD CONSTRAINT log_usuario_fk FOREIGN KEY ( id_usuario )
        REFERENCES usuario ( id );

ALTER TABLE lote
    ADD CONSTRAINT lote_doccom_fk FOREIGN KEY ( n_doc_compras )
        REFERENCES documento_compras ( numero );

ALTER TABLE orden_devolucion
    ADD CONSTRAINT orddev_docven_fk FOREIGN KEY ( numero_doc_ventas )
        REFERENCES documento_de_ventas ( numero );

ALTER TABLE orden_de_venta
    ADD CONSTRAINT ordven_usuario_fk FOREIGN KEY ( id_usuario )
        REFERENCES usuario ( id );

ALTER TABLE pago
    ADD CONSTRAINT pago_docven_fk FOREIGN KEY ( numero_doc_ventas )
        REFERENCES documento_de_ventas ( numero );

ALTER TABLE pago
    ADD CONSTRAINT pago_mpago_fk FOREIGN KEY ( id_metodo_pago )
        REFERENCES metado_de_pago ( id );

ALTER TABLE pago
    ADD CONSTRAINT pago_tipcamper_fk
        FOREIGN KEY ( id_moneda,
                      id_tipo_de_cambio_,
                      id_periodo )
            REFERENCES tipo_cambio_periodo ( id_moneda,
                                             id_tipo_de_cambio,
                                             id_periodo );

ALTER TABLE producto_talla
    ADD CONSTRAINT prodtalla_producto_fk FOREIGN KEY ( id_producto )
        REFERENCES producto ( id );

ALTER TABLE producto_talla
    ADD CONSTRAINT prodtalla_talla_fk FOREIGN KEY ( id_talla )
        REFERENCES talla ( id );

ALTER TABLE producto
    ADD CONSTRAINT producto_categoria_fk FOREIGN KEY ( id_categoria )
        REFERENCES categoria ( id );

ALTER TABLE reserva
    ADD CONSTRAINT reserva_docven_fk FOREIGN KEY ( numero_doc_ventas )
        REFERENCES documento_de_ventas ( numero );

ALTER TABLE tipo_cambio_periodo
    ADD CONSTRAINT tipcamper_moneda_fk FOREIGN KEY ( id_moneda )
        REFERENCES moneda ( id );

ALTER TABLE tipo_cambio_periodo
    ADD CONSTRAINT tipcamper_periodo_fk FOREIGN KEY ( id_periodo )
        REFERENCES periodo ( id );

ALTER TABLE tipo_cambio_periodo
    ADD CONSTRAINT tipcamper_tipcam_fk FOREIGN KEY ( id_tipo_de_cambio )
        REFERENCES tipo_de_cambio ( id );



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            27
-- CREATE INDEX                             2
-- ALTER TABLE                             57
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
