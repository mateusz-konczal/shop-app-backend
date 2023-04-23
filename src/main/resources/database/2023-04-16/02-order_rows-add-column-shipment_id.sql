--liquibase formatted sql
--changeset mkonczal:14
ALTER TABLE order_rows MODIFY product_id BIGINT;
ALTER TABLE order_rows ADD shipment_id BIGINT;
ALTER TABLE order_rows
    ADD CONSTRAINT fk_order_rows_shipment_id FOREIGN KEY (shipment_id) REFERENCES shipments (id);