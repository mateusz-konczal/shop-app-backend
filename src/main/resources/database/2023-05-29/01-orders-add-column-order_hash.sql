--liquibase formatted sql
--changeset mkonczal:33
ALTER TABLE orders ADD order_hash VARCHAR(12) AFTER user_uuid;