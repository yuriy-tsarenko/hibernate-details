ALTER TABLE categories
    RENAME COLUMN category_id TO id;

ALTER TABLE products
  MODIFY COLUMN id BIGINT;

ALTER TABLE categories
  MODIFY COLUMN id BIGINT;

CREATE TABLE product_categories (
    product_id BIGINT,
    category_id BIGINT,
    PRIMARY KEY(product_id, category_id),
    FOREIGN KEY(product_id) REFERENCES products(id),
    FOREIGN KEY(category_id) REFERENCES categories(id)
);