--liquibase formatted sql
--changeset mkonczal:4
ALTER TABLE products
    ADD full_description TEXT DEFAULT NULL AFTER description;