ALTER TABLE user_account
    DROP COLUMN id;

ALTER TABLE customers
    RENAME COLUMN customer_id TO id;

ALTER TABLE user_account
    ADD COLUMN customer_id BIGINT;

ALTER TABLE user_account
    ADD PRIMARY KEY (customer_id);

ALTER TABLE user_account
    ADD FOREIGN KEY(customer_id) REFERENCES customers(id);