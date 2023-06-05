-- Create the customers table
CREATE TABLE IF NOT EXISTS customers (
    customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
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