--liquibase formatted sql
--changeset mkonczal:25
ALTER TABLE orders ADD user_uuid VARCHAR(36) AFTER payment_id;