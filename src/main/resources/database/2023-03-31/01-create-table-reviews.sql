--liquibase formatted sql
--changeset mkonczal:8
CREATE TABLE reviews
(
    id          BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_id  BIGINT      NOT NULL,
    author_name VARCHAR(60) NOT NULL,
    content     TEXT        NOT NULL
);