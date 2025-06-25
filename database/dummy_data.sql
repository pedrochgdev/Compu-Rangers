-- Insert into USUARIO (two users: an admin and a client)
INSERT INTO USUARIO (username, nombre, telefono, correo, direccion, isAdmin, password)
VALUES 
    ('admin1', 'Admin User', '+1234567890', 'admin@example.com', '123 Admin St', true, '$2a$12$62ZMWlAmDvGxgDTjyJyRT.PA7eIi0/57FMnqhk6sRQ2gBrwoo.KNG'),
    ('client1', 'Client User', '+0987654321', 'client@example.com', '456 Client Ave', false, '$2a$12$5TrHpKLDxq0cUUVu/M86zeEEYjfcNe34VfCsbozDV1sxaU0CJf62G');

-- Insert into ADMIN (for admin1)
INSERT INTO ADMIN (usuario_id, fecha_ingreso)
VALUES (1, '2023-01-01');

-- Insert into CLIENTE (for client1)
INSERT INTO CLIENTE (usuario_id, direccion_envio)
VALUES (2, '456 Client Ave');

-- Insert Categories for cmpurangers Tech Store

-- Top-level category: Electronics
INSERT INTO CATEGORIA (nombre, padre_id) VALUES ('Electronics', NULL);
SET @id_electronics = LAST_INSERT_ID();

-- Subcategories under Electronics
INSERT INTO CATEGORIA (nombre, padre_id) VALUES ('Computers', @id_electronics);
SET @id_computers = LAST_INSERT_ID();
INSERT INTO CATEGORIA (nombre, padre_id) VALUES ('Smartphones', @id_electronics);
SET @id_smartphones = LAST_INSERT_ID();
INSERT INTO CATEGORIA (nombre, padre_id) VALUES ('Accessories', @id_electronics);
SET @id_accessories = LAST_INSERT_ID();
INSERT INTO CATEGORIA (nombre, padre_id) VALUES ('Gaming', @id_electronics);
SET @id_gaming = LAST_INSERT_ID();

-- Subcategories under Computers
INSERT INTO CATEGORIA (nombre, padre_id) VALUES ('Laptops', @id_computers);
SET @id_laptops = LAST_INSERT_ID();
INSERT INTO CATEGORIA (nombre, padre_id) VALUES ('Desktops', @id_computers);
SET @id_desktops = LAST_INSERT_ID();
INSERT INTO CATEGORIA (nombre, padre_id) VALUES ('Tablets', @id_computers);
SET @id_tablets = LAST_INSERT_ID();

-- Subcategories under Accessories
INSERT INTO CATEGORIA (nombre, padre_id) VALUES ('Peripherals', @id_accessories);
SET @id_peripherals = LAST_INSERT_ID();
INSERT INTO CATEGORIA (nombre, padre_id) VALUES ('Storage', @id_accessories);
SET @id_storage = LAST_INSERT_ID();
INSERT INTO CATEGORIA (nombre, padre_id) VALUES ('Networking', @id_accessories);
SET @id_networking = LAST_INSERT_ID();

-- Subcategories under Gaming
INSERT INTO CATEGORIA (nombre, padre_id) VALUES ('Consoles', @id_gaming);
SET @id_consoles = LAST_INSERT_ID();
INSERT INTO CATEGORIA (nombre, padre_id) VALUES ('Games', @id_gaming);
SET @id_games = LAST_INSERT_ID();
INSERT INTO CATEGORIA (nombre, padre_id) VALUES ('Gaming Accessories', @id_gaming);
SET @id_gaming_accessories = LAST_INSERT_ID();

-- Insert Brands
INSERT INTO MARCA (nombre) VALUES 
('Apple'),
('Samsung'),
('Dell'),
('HP'),
('Lenovo'),
('Asus'),
('Microsoft'),
('Sony'),
('Nintendo'),
('Logitech'),
('Seagate'),
('Western Digital'),
('TP-Link'),
('Netgear'),
('Google'),
('Motorola'),
('Razer'),
('Corsair');

-- Insert 50 Products
INSERT INTO PRODUCTO (nombre, descripcion, sku, precio_venta, categoria_id, marca_id) VALUES
-- Laptops (8 products)
('MacBook Air M2', 'Laptop Apple MacBook Air with M2 chip', 'MBAIRM2', 1199.99, @id_laptops, 1),
('Dell XPS 13', 'Laptop Dell XPS 13', 'XPS13', 999.99, @id_laptops, 3),
('HP Spectre x360', 'Laptop HP Spectre x360', 'SPECTREX360', 1299.99, @id_laptops, 4),
('Lenovo ThinkPad X1', 'Laptop Lenovo ThinkPad X1 Carbon', 'THINKPADX1', 1399.99, @id_laptops, 5),
('Asus ZenBook 14', 'Laptop Asus ZenBook 14', 'ZENBOOK14', 899.99, @id_laptops, 6),
('Surface Laptop 5', 'Laptop Microsoft Surface Laptop 5', 'SURFACELAP5', 1299.99, @id_laptops, 7),
('MacBook Pro 14', 'Laptop Apple MacBook Pro 14-inch', 'MBPRO14', 1999.99, @id_laptops, 1),
('Dell Inspiron 15', 'Laptop Dell Inspiron 15', 'INSPIRON15', 699.99, @id_laptops, 3),
-- Desktops (4 products)
('iMac 24', 'Desktop Apple iMac 24-inch', 'IMAC24', 1299.99, @id_desktops, 1),
('Dell OptiPlex 7090', 'Desktop Dell OptiPlex 7090', 'OPTIPLEX7090', 899.99, @id_desktops, 3),
('HP Pavilion Desktop', 'Desktop HP Pavilion', 'PAVILIONDT', 799.99, @id_desktops, 4),
('Lenovo IdeaCentre 5', 'Desktop Lenovo IdeaCentre 5', 'IDEACENTRE5', 699.99, @id_desktops, 5),
-- Tablets (4 products)
('iPad Pro 12.9', 'Tablet Apple iPad Pro 12.9-inch', 'IPADPRO129', 1099.99, @id_tablets, 1),
('Galaxy Tab S8', 'Tablet Samsung Galaxy Tab S8', 'GALTABS8', 699.99, @id_tablets, 2),
('Surface Pro 9', 'Tablet Microsoft Surface Pro 9', 'SURFACEPRO9', 999.99, @id_tablets, 7),
('Lenovo Tab P11 Pro', 'Tablet Lenovo Tab P11 Pro', 'TABP11PRO', 499.99, @id_tablets, 5),
-- Smartphones (8 products)
('iPhone 14', 'Smartphone Apple iPhone 14', 'IPHONE14', 999.99, @id_smartphones, 1),
('Galaxy S22', 'Smartphone Samsung Galaxy S22', 'GALAXYS22', 799.99, @id_smartphones, 2),
('Google Pixel 7', 'Smartphone Google Pixel 7', 'PIXEL7', 599.99, @id_smartphones, 15),
('Moto G Power', 'Smartphone Motorola Moto G Power', 'MOTOGPOWER', 249.99, @id_smartphones, 16),
('iPhone 13', 'Smartphone Apple iPhone 13', 'IPHONE13', 799.99, @id_smartphones, 1),
('Galaxy A53', 'Smartphone Samsung Galaxy A53', 'GALAXYA53', 449.99, @id_smartphones, 2),
('Google Pixel 6a', 'Smartphone Google Pixel 6a', 'PIXEL6A', 449.99, @id_smartphones, 15),
('Motorola Edge 30', 'Smartphone Motorola Edge 30', 'MOTOEDGE30', 499.99, @id_smartphones, 16),
-- Peripherals (4 products)
('Logitech MX Master 3', 'Mouse Logitech MX Master 3', 'MXMASTER3', 99.99, @id_peripherals, 10),
('Magic Keyboard', 'Keyboard Apple Magic Keyboard', 'MAGICKEYBOARD', 99.99, @id_peripherals, 1),
('Dell Ultrasharp 27', 'Monitor Dell Ultrasharp 27-inch', 'ULTRASHARP27', 399.99, @id_peripherals, 3),
('HP LaserJet Pro', 'Printer HP LaserJet Pro', 'LASERJETPRO', 199.99, @id_peripherals, 4),
-- Storage (4 products)
('Seagate HDD 2TB', 'External Hard Drive Seagate 2TB', 'SEAGATE2TB', 79.99, @id_storage, 11),
('WD My Passport 4TB', 'External Hard Drive WD My Passport 4TB', 'WDMYPASSPORT4TB', 119.99, @id_storage, 12),
('Samsung T7 SSD 1TB', 'External SSD Samsung T7 1TB', 'SAMSUNGT71TB', 149.99, @id_storage, 2),
('Seagate USB 128GB', 'USB Drive Seagate 128GB', 'SEAGATEUSB128', 29.99, @id_storage, 11),
-- Networking (4 products)
('TP-Link Archer AX50', 'Router TP-Link Archer AX50', 'ARCHERAX50', 149.99, @id_networking, 13),
('Netgear Nighthawk R7000', 'Router Netgear Nighthawk R7000', 'NIGHTHAWKR7000', 189.99, @id_networking, 14),
('TP-Link TL-SG108', 'Switch TP-Link TL-SG108', 'TLSG108', 29.99, @id_networking, 13),
('Netgear CM1000', 'Modem Netgear CM1000', 'CM1000', 159.99, @id_networking, 14),
-- Consoles (3 products)
('PlayStation 5', 'Console Sony PlayStation 5', 'PS5', 499.99, @id_consoles, 8),
('Xbox Series X', 'Console Microsoft Xbox Series X', 'XBOXSERIESX', 499.99, @id_consoles, 7),
('Nintendo Switch', 'Console Nintendo Switch', 'SWITCH', 299.99, @id_consoles, 9),
-- Games (5 products)
('Elden Ring', 'Game Elden Ring for PS5', 'ELDENRINGPS5', 59.99, @id_games, 8),
('Halo Infinite', 'Game Halo Infinite for Xbox', 'HALOINFINITE', 59.99, @id_games, 7),
('Zelda: Breath of the Wild', 'Game for Nintendo Switch', 'ZELDABOTW', 59.99, @id_games, 9),
('God of War Ragnarok', 'Game for PS5', 'GOWRAGNAROK', 69.99, @id_games, 8),
('Forza Horizon 5', 'Game for Xbox', 'FORZAHORIZON5', 59.99, @id_games, 7),
-- Gaming Accessories (5 products)
('Razer BlackShark V2', 'Gaming Headset Razer BlackShark V2', 'RAZERBSV2', 99.99, @id_gaming_accessories, 17),
('Corsair K95 RGB', 'Gaming Keyboard Corsair K95 RGB', 'CORSAIRK95', 199.99, @id_gaming_accessories, 18),
('Logitech G502', 'Gaming Mouse Logitech G502', 'LOGITECHG502', 79.99, @id_gaming_accessories, 10),
('DualSense Controller', 'Controller for PS5', 'DUALSENSE', 69.99, @id_gaming_accessories, 8),
('Xbox Wireless Controller', 'Controller for Xbox', 'XBOXCONTROLLER', 59.99, @id_gaming_accessories, 7),
('Nintendo Switch Pro Controller', 'Controller for Nintendo Switch', 'SWITCHPRO', 69.99, @id_gaming_accessories, 9);

-- Insert into CARRITO (cart for client1)
INSERT INTO CARRITO (cantidad_productos, total, cliente_usuario_id)
VALUES (0, 0, 2);

-- Insert into ORDEN_DE_VENTA (order from the cart)
INSERT INTO ORDEN_DE_VENTA (estado, fecha, total, cliente_usuario_id, direccion)
VALUES ('PAGADO', CURDATE(), 2799.98, 2, '456 Client Ave');

-- Insert into DOCUMENTO_DE_VENTAS (sales document with tax)
INSERT INTO DOCUMENTO_DE_VENTAS (numero, subtotal, impuestos, total, orden_de_venta_id)
VALUES (1001, 2799.98, 504.00, 3303.98, 1);

-- Insert into ORDEN_DEVOLUCION 
INSERT INTO orden_devolucion (fecha_registro, motivo, tipo_devolucion, documento_de_ventas_numero)
VALUES (CURRENT_DATE, 'Cliente devolvió productos defectuosos', 'REEMBOLSO', 1001);

INSERT INTO PROVEEDOR (razon_social) VALUES ('Tech Distributor Ltd.');
SET @proveedor_id = LAST_INSERT_ID();

-- Insert into DOCUMENTO_COMPRAS (purchase document)
INSERT INTO DOCUMENTO_COMPRAS (numero, subtotal, impuestos, total, proveedor_id) 
VALUES (2001, 0, 0, 0, @proveedor_id);
SET @doc_compra_numero = 2001;

-- Insert Lote
INSERT INTO LOTE (fecha_creacion, estado, documento_compras_numero) 
VALUES (CURDATE(), 'ABIERTO', @doc_compra_numero);
SET @lote_id = LAST_INSERT_ID();

-- Insert Lot Details (Purchase Prices = 75% of Selling Prices)
INSERT INTO DETALLE_LOTE (cantidad, precio_compra, lote_id, producto_id) VALUES
(10, 899.99, @lote_id, 1),   -- MacBook Air M2
(10, 749.99, @lote_id, 2),   -- Dell XPS 13
(10, 974.99, @lote_id, 3),   -- HP Spectre x360
(10, 1049.99, @lote_id, 4),  -- Lenovo ThinkPad X1
(10, 674.99, @lote_id, 5),   -- Asus ZenBook 14
(10, 974.99, @lote_id, 6),   -- Surface Laptop 5
(10, 1499.99, @lote_id, 7),  -- MacBook Pro 14
(10, 524.99, @lote_id, 8),   -- Dell Inspiron 15
(10, 974.99, @lote_id, 9),   -- iMac 24
(10, 674.99, @lote_id, 10),  -- Dell OptiPlex 7090
(10, 599.99, @lote_id, 11),  -- HP Pavilion Desktop
(10, 524.99, @lote_id, 12),  -- Lenovo IdeaCentre 5
(10, 824.99, @lote_id, 13),  -- iPad Pro 12.9
(10, 524.99, @lote_id, 14),  -- Galaxy Tab S8
(10, 749.99, @lote_id, 15),  -- Surface Pro 9
(10, 374.99, @lote_id, 16),  -- Lenovo Tab P11 Pro
(10, 749.99, @lote_id, 17),  -- iPhone 14
(10, 599.99, @lote_id, 18),  -- Galaxy S22
(10, 449.99, @lote_id, 19),  -- Google Pixel 7
(10, 187.49, @lote_id, 20),  -- Moto G Power
(10, 599.99, @lote_id, 21),  -- iPhone 13
(10, 337.49, @lote_id, 22),  -- Galaxy A53
(10, 337.49, @lote_id, 23),  -- Google Pixel 6a
(10, 374.99, @lote_id, 24),  -- Motorola Edge 30
(10, 74.99, @lote_id, 25),   -- Logitech MX Master 3
(10, 74.99, @lote_id, 26),   -- Magic Keyboard
(10, 299.99, @lote_id, 27),  -- Dell Ultrasharp 27
(10, 149.99, @lote_id, 28),  -- HP LaserJet Pro
(10, 59.99, @lote_id, 29),   -- Seagate HDD 2TB
(10, 89.99, @lote_id, 30),   -- WD My Passport 4TB
(10, 112.49, @lote_id, 31),  -- Samsung T7 SSD 1TB
(10, 22.49, @lote_id, 32),   -- Seagate USB 128GB
(10, 112.49, @lote_id, 33),  -- TP-Link Archer AX50
(10, 142.49, @lote_id, 34),  -- Netgear Nighthawk R7000
(10, 22.49, @lote_id, 35),   -- TP-Link TL-SG108
(10, 119.99, @lote_id, 36),  -- Netgear CM1000
(10, 374.99, @lote_id, 37),  -- PlayStation 5
(10, 374.99, @lote_id, 38),  -- Xbox Series X
(10, 224.99, @lote_id, 39),  -- Nintendo Switch
(10, 44.99, @lote_id, 40),   -- Elden Ring
(10, 44.99, @lote_id, 41),   -- Halo Infinite
(10, 44.99, @lote_id, 42),   -- Zelda: Breath of the Wild
(10, 52.49, @lote_id, 43),   -- God of War Ragnarok
(10, 44.99, @lote_id, 44),   -- Forza Horizon 5
(10, 74.99, @lote_id, 45),   -- Razer BlackShark V2
(10, 149.99, @lote_id, 46),  -- Corsair K95 RGB
(10, 59.99, @lote_id, 47),   -- Logitech G502
(10, 52.49, @lote_id, 48),   -- DualSense Controller
(10, 44.99, @lote_id, 49),   -- Xbox Wireless Controller
(10, 52.49, @lote_id, 50);	 -- Nintendo Switch Pro Controller

-- Insert into INVENTARIO (inventory for the lot)
INSERT INTO INVENTARIO (cantidad_disponible, lote_id, producto_id) VALUES
(10, @lote_id, 1),
(10, @lote_id, 2),
(10, @lote_id, 3),
(10, @lote_id, 4),
(10, @lote_id, 5),
(10, @lote_id, 6),
(10, @lote_id, 7),
(10, @lote_id, 8),
(10, @lote_id, 9),
(10, @lote_id, 10),
(10, @lote_id, 11),
(10, @lote_id, 12),
(10, @lote_id, 13),
(10, @lote_id, 14),
(10, @lote_id, 15),
(10, @lote_id, 16),
(10, @lote_id, 17),
(10, @lote_id, 18),
(10, @lote_id, 19),
(10, @lote_id, 20),
(10, @lote_id, 21),
(10, @lote_id, 22),
(10, @lote_id, 23),
(10, @lote_id, 24),
(10, @lote_id, 25),
(10, @lote_id, 26),
(10, @lote_id, 27),
(10, @lote_id, 28),
(10, @lote_id, 29),
(10, @lote_id, 30),
(10, @lote_id, 31),
(10, @lote_id, 32),
(10, @lote_id, 33),
(10, @lote_id, 34),
(10, @lote_id, 35),
(10, @lote_id, 36),
(10, @lote_id, 37),
(10, @lote_id, 38),
(10, @lote_id, 39),
(10, @lote_id, 40),
(10, @lote_id, 41),
(10, @lote_id, 42),
(10, @lote_id, 43),
(10, @lote_id, 44),
(10, @lote_id, 45),
(10, @lote_id, 46),
(10, @lote_id, 47),
(10, @lote_id, 48),
(10, @lote_id, 49),
(10, @lote_id, 50);


-- Insert into IMPUESTO (tax type)
INSERT INTO IMPUESTO (nombre, abreviacion, tipo)
VALUES ('Impuesto General a las ventas', 'IGV', 'VENTA');

-- Insert into DETALLE_VENTA (order details)
INSERT INTO DETALLE_VENTA (cantidad, subtotal, orden_de_venta_id, inventario_id)
VALUES 
    (1, 1999.99, 1, 1),  -- MacBook Pro
    (1, 799.99, 1, 2);   -- Galaxy S21

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
    ('SOL', 'Sol Peruano'),
    ('USD', 'US Dollar');

-- Insert into MONEDA_PERIODO (exchange rates for the period)
INSERT INTO MONEDA_PERIODO (moneda_id, periodo_id, tipoCambio, estado, valor)
VALUES 
    (1, 1, 'COMPRA', 'ACTIVO', 1.00),  -- USD buy rate
    (1, 1, 'VENTA', 'ACTIVO', 1.00),   -- USD sell rate
    (2, 1, 'COMPRA', 'ACTIVO', 3.60),  -- EUR buy rate
    (2, 1, 'VENTA', 'ACTIVO', 3.80);   -- EUR sell rate

-- Insert into METODO_DE_PAGO (payment methods)
INSERT INTO METODO_DE_PAGO (nombre, descripcion, estado)
VALUES 
    ('IZIPAY', 'Payment by credit card', 'ACTIVO'),
    ('POS', 'Payment by credit card', 'ACTIVO'),
    ('PAYPAL', 'Payment by PayPal', 'ACTIVO');

-- Insert into PAGO (payment for the sales document)
-- Note: Using 0 for documento_de_compras_numero as a workaround since it’s NOT NULL but not applicable here
INSERT INTO PAGO (monto, fecha_pago, estado, referencia, documento_de_ventas_numero, documento_de_compras_numero, metodo_de_pago_id, moneda_periodo_id)
VALUES (3303.98, CURDATE(), 'PENDIENTE', 'CC12345', 1001, NULL, 1, 1),
		(3303.98, CURDATE(), 'PENDIENTE', 'CC12345', NULL, 2001, 2, 2);

-- Insert into LOG (log entry for admin)
INSERT INTO LOG (accion, fecha, usuario_id)
VALUES ('Logged in', CURDATE(), 1);
