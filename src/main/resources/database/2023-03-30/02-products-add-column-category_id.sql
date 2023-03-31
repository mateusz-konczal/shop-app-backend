--liquibase formatted sql
--changeset mkonczal:6
ALTER TABLE products ADD category_id BIGINT AFTER category;
ALTER TABLE products DROP COLUMN category;
ALTER TABLE products
    ADD CONSTRAINT fk_products_category_id FOREIGN KEY (category_id) REFERENCES categories (id);