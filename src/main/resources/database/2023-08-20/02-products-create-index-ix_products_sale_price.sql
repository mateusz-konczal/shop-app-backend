--liquibase formatted sql
--changeset mkonczal:46
CREATE INDEX ix_products_sale_price ON products (sale_price);