--liquibase formatted sql
--changeset mkonczal:34
ALTER TABLE products ADD sale_price DECIMAL(9, 2) AFTER price;