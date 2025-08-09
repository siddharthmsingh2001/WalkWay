DROP DATABASE IF EXISTS inventorydb;
CREATE DATABASE IF NOT EXISTS inventorydb;
USE inventorydb;

CREATE TABLE inventory_warehouse (
    warehouse_id INT PRIMARY KEY AUTO_INCREMENT,
    warehouse_name VARCHAR(100) UNIQUE NOT NULL,
    warehouse_location_id INT REFERENCES inventory_location(location_id) ON DELETE SET NULL,
    warehouse_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    warehouse_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE inventory_location(
	location_id INT PRIMARY KEY AUTO_INCREMENT,
    location_address VARCHAR(100) NULL,
    location_city VARCHAR(100) NOT NULL,
    location_postal INT NOT NULL,
    location_state VARCHAR(100) NOT NULL
);

CREATE TABLE inventory_product_snapshot(
	product_snapshot_id INT PRIMARY KEY AUTO_INCREMENT,
	product_code INT UNIQUE NOT NULL,
    product_snapshot_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    product_snapshot_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE inventory_stock(
	stock_id INT PRIMARY KEY AUTO_INCREMENT,
    stock_warehouse_id INT NOT NULL REFERENCES inventory_warehouse(warehouse_id) ON DELETE CASCADE ON UPDATE CASCADE,
    stock_product_snapshot_id INT NOT NULL REFERENCES inventory_product_snapshot(product_snapshot_id) ON DELETE CASCADE ON UPDATE CASCADE,
    stock_quantity_available INT NOT NULL CHECK(stock_quantity_available >= 0),
    stock_quantity_reserved INT DEFAULT 0 CHECK(stock_quantity_reserved >= 0),
    stock_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    stock_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE(stock_warehouse_id,stock_product_snapshot_id)
);

CREATE TABLE inventory_transaction(
	transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    transaction_stock_id INT NOT NULL REFERENCES inventory_stock(stock_id) ON DELETE CASCADE ON UPDATE CASCADE,
    transaction_change INT NOT NULL,
    transaction_reference_id INT NOT NULL,
    transaction_reference_type ENUM('ORDER','STOCK') NOT NULL,
    transaction_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO inventory_location(location_address, location_city, location_postal, location_state) VALUES
('GHANSOLI', 'NAVI MUMBAI', 400701, 'MAHARASHTRA'),
('KANDIVALI', 'MUMBAI', 400101, 'MAHARASHTRA');

INSERT INTO inventory_warehouse(warehouse_name, warehouse_location_id) VALUES
('Ghansoli Storeroom', 1),
('Kandivali Depot',2);

INSERT INTO inventory_stock(stock_warehouse_id, stock_product_snapshot_id, stock_quantity_available, stock_quantity_reserved) VALUES
(1, 1, 20, 10);