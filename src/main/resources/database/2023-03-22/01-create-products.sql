--liquibase formatted sql
--changeset mkonczal:1
CREATE TABLE products
(
    id          BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)  NOT NULL,
    category    VARCHAR(255)  NOT NULL,
    description TEXT          NOT NULL,
    price       DECIMAL(9, 2) NOT NULL,
    currency    VARCHAR(3)    NOT NULL
);