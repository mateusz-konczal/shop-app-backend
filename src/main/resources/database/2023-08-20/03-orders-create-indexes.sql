--liquibase formatted sql
--changeset mkonczal:47
CREATE INDEX ix_orders_place_date ON orders (place_date);

--changeset mkonczal:48
CREATE INDEX ix_orders_user_uuid ON orders (user_uuid);

--changeset mkonczal:49
CREATE UNIQUE INDEX ix_orders_order_hash ON orders (order_hash);