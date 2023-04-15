--liquibase formatted sql
--changeset mkonczal:12
CREATE TABLE orders
(
    id           BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    place_date   DATETIME      NOT NULL,
    order_status VARCHAR(32)   NOT NULL,
    total_value  DECIMAL(9, 2) NOT NULL,
    first_name   VARCHAR(64)   NOT NULL,
    last_name    VARCHAR(64)   NOT NULL,
    street       VARCHAR(80)   NOT NULL,
    zip_code      VARCHAR(6)    NOT NULL,
    city         VARCHAR(64)   NOT NULL,
    email        VARCHAR(64)   NOT NULL,
    phone        VARCHAR(16)   NOT NULL
);
CREATE TABLE order_rows
(
    id         BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_id   BIGINT        NOT NULL,
    product_id BIGINT        NOT NULL,
    quantity   INT           NOT NULL,
    price      DECIMAL(9, 2) NOT NULL,
    CONSTRAINT fk_order_rows_order_id FOREIGN KEY (order_id) REFERENCES orders (id),
    CONSTRAINT fk_order_rows_product_id FOREIGN KEY (product_id) REFERENCES products (id)
);