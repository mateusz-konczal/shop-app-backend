--liquibase formatted sql
--changeset mkonczal:16
ALTER TABLE orders ADD payment_id BIGINT;
UPDATE orders SET payment_id = 1;
ALTER TABLE orders MODIFY payment_id BIGINT NOT NULL;
ALTER TABLE orders
    ADD CONSTRAINT fk_orders_payment_id FOREIGN KEY (payment_id) REFERENCES payments (id);