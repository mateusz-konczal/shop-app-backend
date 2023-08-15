--liquibase formatted sql
--changeset mkonczal:43
ALTER TABLE cart_items MODIFY created DATETIME NOT NULL;
ALTER TABLE cart_items MODIFY quantity INT NOT NULL;