-- Create the customers table
CREATE TABLE IF NOT EXISTS customers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(50),
    contact_name VARCHAR(50),
    country VARCHAR(50)
);

DELETE FROM customers;

-- insert statements for Table 1: customers
INSERT INTO customers (customer_name, contact_name, country) VALUES
('Customer 1', 'John Smith', 'USA'),
('Customer 2', 'Jane Doe', 'Canada'),
('Customer 3', 'Miguel Rodriguez', 'Mexico');

CREATE TABLE IF NOT EXISTS user_account (
    username VARCHAR(50),
    password VARCHAR(255),
    customer_id BIGINT PRIMARY KEY,
    FOREIGN KEY(customer_id) REFERENCES customers(id)
);

-- Create the products table
CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(50),
    supplier_id INT,
    unit_price DECIMAL(10,2),
    customer_id BIGINT,
    FOREIGN KEY(customer_id) REFERENCES customers(id)
);

-- Create the categories table
CREATE TABLE IF NOT EXISTS categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(50),
    description VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS products_categories (
    product_id BIGINT,
    category_id BIGINT,
    PRIMARY KEY(product_id, category_id),
    FOREIGN KEY(product_id) REFERENCES products(id),
    FOREIGN KEY(category_id) REFERENCES categories(id)
);