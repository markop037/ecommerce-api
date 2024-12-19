-- Create the database
CREATE DATABASE ecommerce;

-- Use the ecommerce database
USE ecommerce;

-- Create the User table
CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'CUSTOMER') NOT NULL
);

-- Create the Product table
CREATE TABLE Product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL
);

-- Create the Cart table
CREATE TABLE Cart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    totalPrice DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id)
);

-- Create the Cart_Product table (Many-to-Many relationship between Cart and Product)
CREATE TABLE Cart_Product (
    cart_id INT NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY (cart_id, product_id),
    FOREIGN KEY (cart_id) REFERENCES Cart(id),
    FOREIGN KEY (product_id) REFERENCES Product(id)
);

-- Create the Order table
CREATE TABLE `Order` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    totalPrice DECIMAL(10, 2) NOT NULL,
    status ENUM('PENDING', 'COMPLETED', 'CANCELLED') NOT NULL,
    orderDate DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id)
);

-- Create the Order_Product table (One-to-Many relationship between Order and Product)
CREATE TABLE Order_Product (
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES `Order`(id),
    FOREIGN KEY (product_id) REFERENCES Product(id)
);


