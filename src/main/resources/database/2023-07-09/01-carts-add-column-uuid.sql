--liquibase formatted sql
--changeset mkonczal:40
ALTER TABLE carts ADD uuid VARCHAR(36) NOT NULL UNIQUE AFTER id;