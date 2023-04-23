--liquibase formatted sql
--changeset mkonczal:17
ALTER TABLE orders ADD house_number VARCHAR(6) AFTER street;
UPDATE orders SET house_number = '1';
ALTER TABLE orders MODIFY house_number VARCHAR (6) NOT NULL;