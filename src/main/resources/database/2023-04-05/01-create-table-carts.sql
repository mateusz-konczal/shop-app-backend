--liquibase formatted sql
--changeset mkonczal:10
CREATE TABLE carts
(
    id      BIGINT   NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created DATETIME NOT NULL
);
CREATE TABLE cart_items
(
    id         BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    quantity   INT,
    CONSTRAINT fk_cart_items_product_id FOREIGN KEY (product_id) REFERENCES products (id)
);