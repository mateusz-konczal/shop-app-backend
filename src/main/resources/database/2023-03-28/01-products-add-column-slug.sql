--liquibase formatted sql
--changeset mkonczal:3
ALTER TABLE products
    ADD slug VARCHAR(255) AFTER image;
ALTER TABLE products
    ADD CONSTRAINT ui_products_slug UNIQUE (slug);