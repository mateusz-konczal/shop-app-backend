--liquibase formatted sql
--changeset mkonczal:18
ALTER TABLE orders
    ADD apartment_number VARCHAR(6) DEFAULT NULL AFTER house_number;