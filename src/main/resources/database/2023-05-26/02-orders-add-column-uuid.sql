--liquibase formatted sql
--changeset mkonczal:31
ALTER TABLE orders ADD uuid VARCHAR(36) AFTER id;

--changeset mkonczal:32
ALTER TABLE orders MODIFY uuid VARCHAR(36) NOT NULL UNIQUE;