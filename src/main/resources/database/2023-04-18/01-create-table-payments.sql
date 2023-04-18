--liquibase formatted sql
--changeset mkonczal:15
CREATE TABLE payments
(
    id              BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(64) NOT NULL,
    type            VARCHAR(32) NOT NULL,
    default_payment BOOLEAN DEFAULT FALSE,
    note            TEXT
);