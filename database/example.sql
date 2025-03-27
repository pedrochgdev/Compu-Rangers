USE april_boutique_db;
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    stock INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
SHOW TABLES;
DESCRIBE products;

INSERT INTO products (name, description, price, stock) VALUES
('Camiseta Oversize', 'Camiseta de algodón premium, color negro', 49.99, 15),
('Jeans Skinny', 'Jeans azul oscuro con diseño ajustado', 89.90, 10),
('Zapatillas Urbanas', 'Zapatillas deportivas blancas con detalles en rojo', 129.50, 20),
('Chaqueta de Cuero', 'Chaqueta negra de cuero sintético', 199.99, 5),
('Gorra Snapback', 'Gorra ajustable con logo bordado', 29.99, 25);


select * from products;