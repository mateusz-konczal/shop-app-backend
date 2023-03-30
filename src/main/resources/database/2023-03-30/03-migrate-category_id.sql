--liquibase formatted sql
--changeset mkonczal:7
INSERT INTO categories (id, name, description, slug) VALUES (1, 'Inne', '', 'inne');
UPDATE products SET category_id = 1;
ALTER TABLE products MODIFY category_id BIGINT NOT NULL;