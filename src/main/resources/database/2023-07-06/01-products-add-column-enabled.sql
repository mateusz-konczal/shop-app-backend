--liquibase formatted sql
--changeset mkonczal:39
ALTER TABLE products ADD enabled BOOLEAN DEFAULT TRUE AFTER slug;