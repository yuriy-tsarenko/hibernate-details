ALTER TABLE products
    RENAME COLUMN product_id TO id;

ALTER TABLE products
    ADD COLUMN customer_id BIGINT;

ALTER TABLE products
    ADD FOREIGN KEY(customer_id) REFERENCES customers(id);