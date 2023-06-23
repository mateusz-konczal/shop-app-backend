--liquibase formatted sql
--changeset mkonczal:36
ALTER TABLE shipments ADD enabled BOOLEAN DEFAULT TRUE AFTER default_shipment;