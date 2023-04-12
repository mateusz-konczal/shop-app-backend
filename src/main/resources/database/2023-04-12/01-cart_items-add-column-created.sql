--liquibase formatted sql
--changeset mkonczal:11
ALTER TABLE cart_items
    ADD created DATETIME AFTER id;