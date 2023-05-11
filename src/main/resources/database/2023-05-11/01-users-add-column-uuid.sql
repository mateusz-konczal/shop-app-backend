--liquibase formatted sql
--changeset mkonczal:24
ALTER TABLE users ADD uuid VARCHAR(36) AFTER id;
UPDATE users SET uuid = uuid();
ALTER TABLE users MODIFY uuid VARCHAR(36) NOT NULL UNIQUE;