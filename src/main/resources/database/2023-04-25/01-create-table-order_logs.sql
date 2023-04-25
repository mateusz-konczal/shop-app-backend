--liquibase formatted sql
--changeset mkonczal:19
CREATE TABLE order_logs
(
    id       BIGINT   NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT   NOT NULL,
    created  DATETIME NOT NULL,
    note     TEXT,
    CONSTRAINT fk_order_logs_order_id FOREIGN KEY (order_id) REFERENCES orders (id)
);