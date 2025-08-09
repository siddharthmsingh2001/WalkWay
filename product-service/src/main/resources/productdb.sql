DROP DATABASE IF EXISTS productdb;
CREATE DATABASE IF NOT EXISTS productdb;
USE productdb;

CREATE TABLE product_gender (
	product_gender_id TINYINT PRIMARY KEY AUTO_INCREMENT,
	product_gender_name VARCHAR(10) UNIQUE NOT NULL,
	product_gender_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	product_gender_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE product_category(
	product_category_id TINYINT PRIMARY KEY AUTO_INCREMENT,
    product_category_name VARCHAR(25) NOT NULL,
    product_parent_category TINYINT NULL REFERENCES product_category(product_category_id) ON DELETE SET NULL ON UPDATE CASCADE,
    product_category_image VARCHAR(255) NULL,
    product_category_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	product_category_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE product(
	product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) UNIQUE NOT NULL,
    product_category_id TINYINT NOT NULL REFERENCES product_category(product_category_id) ON DELETE CASCADE ON UPDATE CASCADE,
    product_gender_id TINYINT NOT NULL REFERENCES product_gender(product_gender_id) ON DELETE CASCADE ON UPDATE CASCADE,
    product_description TEXT,
	product_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	product_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- CREATE TABLE product_category_closure (
--     ancestor_id TINYINT NOT NULL REFERENCES product_category(product_category_id) ON DELETE CASCADE,
--     descendant_id TINYINT NOT NULL REFERENCES product_category(product_category_id) ON DELETE CASCADE,
--     depth TINYINT NOT NULL,
--     PRIMARY KEY (ancestor_id, descendant_id),
--     KEY idx_descendant (descendant_id)
-- );

--CREATE TABLE product_item_colour (
--    product_item_id INT NOT NULL REFERENCES product_item(product_item_id) ON DELETE CASCADE,
--    product_colour_id TINYINT NOT NULL REFERENCES product_colour(product_colour_id) ON DELETE CASCADE,
--    PRIMARY KEY (product_item_id, product_colour_id)
--);

-- Inventory will map here
CREATE TABLE product_item(
	product_item_id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT NOT NULL REFERENCES product(product_id) ON DELETE CASCADE ON UPDATE CASCADE,
    product_item_colour_id TINYINT NOT NULL REFERENCES product_colour(product_colour_id) ON DELETE SET NULL ON UPDATE CASCADE, -- Comment this our if you decide to normalize colour.
    product_item_size_id TINYINT NOT NULL REFERENCES product_size(product_size_id) ON DELETE SET NULL ON UPDATE CASCADE,
    product_item_original_price INT NOT NULL CHECK (product_item_original_price >= 0),
    product_item_sale_price INT NULL,
    product_item_code INT UNIQUE NOT NULL,
	product_item_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	product_item_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE product_image(
	product_image_id INT PRIMARY KEY AUTO_INCREMENT,
    product_item_id INT NOT NULL REFERENCES product_item(product_item_id) ON DELETE CASCADE ON UPDATE CASCADE,
    product_image_url VARCHAR(255) NOT NULL,
	product_image_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	product_image_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE product_colour (
	product_colour_id TINYINT PRIMARY KEY AUTO_INCREMENT,
    product_colour_name VARCHAR(15) UNIQUE NOT NULL,
	product_colour_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	product_colour_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE product_size (
	product_size_id TINYINT PRIMARY KEY AUTO_INCREMENT,
    product_size_name VARCHAR(10) UNIQUE NOT NULL,
    product_size_order TINYINT UNIQUE NOT NULL,
	product_size_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	product_size_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO product_gender (product_gender_name) VALUES
('MEN'),
('WOMEN');

INSERT INTO product_size (product_size_name, product_size_order) VALUES
('11', 1),
('11.5', 2),
('12', 3),
('12.5', 4);

INSERT INTO product_colour (product_colour_name) VALUES
('BLACK'),
('WHITE'),
('GREY'),
('BLUE');

INSERT INTO product_category (product_category_name, product_parent_category, product_category_image) VALUES
('SPORTS', NULL, NULL),
('CASUALS', NULL, NULL),
('FLIP-FLOPS', 2, NULL),
('BASKETBALL', 1, NULL),
('FOOTBALL', 1, NULL);

INSERT INTO product (product_name, product_category_id, product_gender_id, product_description) VALUES
('Nike Elevate 3', 4, 1, 'Level up your game on both ends of the floor in the Nike Renew Elevate 3'),
('Giannis Freak 6', 4, 1, 'Giannis needs a shoe to dominate the modern positionless game'),
('OffCourt', 3, 2, 'Featuring Revive Foam, a super-soft foam designed to help you relax and recharge, the unrelenting comfort of the Nike Offcourt Slide is sensational');

--[
--  {
--    "productId": 1,
--    "productColourId": 1,
--    "productSizeId": 1,
--    "productItemOriginalPrice": 7095,
--    "productItemSalePrice": 7095,
--    "productItemCode": 93049002
--  },
--  {
--    "productId": 1,
--    "productColourId": 3,
--    "productSizeId": 1,
--    "productItemOriginalPrice": 7095,
--    "productItemSalePrice": 7095,
--    "productItemCode": 93049003
--  },
--  {
--    "productId": 2,
--    "productColourId": 4,
--    "productSizeId": 1,
--    "productItemOriginalPrice": 7000,
--    "productItemSalePrice": 7000,
--    "productItemCode": 93051003
--  },
--  {
--    "productId": 2,
--    "productColourId": 4,
--    "productSizeId": 2,
--    "productItemOriginalPrice": 7000,
--    "productItemSalePrice": 7000,
--    "productItemCode": 93052003
--  },
--  {
--    "productId": 2,
--    "productColourId": 4,
--    "productSizeId": 3,
--    "productItemOriginalPrice": 7000,
--    "productItemSalePrice": 7000,
--    "productItemCode": 93053003
--  },
--  {
--    "productId": 3,
--    "productColourId": 1,
--    "productSizeId": 1,
--    "productItemOriginalPrice": 2000,
--    "productItemSalePrice": 2000,
--    "productItemCode": 93011003
--  }
--]