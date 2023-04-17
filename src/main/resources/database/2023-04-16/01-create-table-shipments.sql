--liquibase formatted sql
--changeset mkonczal:13
CREATE TABLE shipments
(
    id               BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(64)   NOT NULL,
    price            DECIMAL(9, 2) NOT NULL,
    type             VARCHAR(32)   NOT NULL,
    default_shipment BOOLEAN DEFAULT FALSE
);