--liquibase formatted sql
--changeset mkonczal:35
ALTER TABLE payments ADD enabled BOOLEAN DEFAULT TRUE AFTER default_payment;