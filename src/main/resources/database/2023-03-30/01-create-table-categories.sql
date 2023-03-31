--liquibase formatted sql
--changeset mkonczal:5
CREATE TABLE categories
(
    id          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    slug        VARCHAR(255) NOT NULL
);