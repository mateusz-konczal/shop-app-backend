--liquibase formatted sql
--changeset mkonczal:44
ALTER TABLE order_logs MODIFY note TEXT NOT NULL;