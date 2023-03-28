--liquibase formatted sql
--changeset mkonczal:2
ALTER TABLE products
    ADD image VARCHAR(128) AFTER currency;