--liquibase formatted sql
--changeset mkonczal:30
UPDATE payments SET default_payment = false;
INSERT INTO payments(name, type, default_payment, note)
VALUES ('Płatność online Przelewy24', 'P24_ONLINE', true, '');