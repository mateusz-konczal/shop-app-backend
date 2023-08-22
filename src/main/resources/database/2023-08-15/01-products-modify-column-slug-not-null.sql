--liquibase formatted sql
--changeset mkonczal:41
ALTER TABLE products MODIFY slug VARCHAR(255) NOT NULL;