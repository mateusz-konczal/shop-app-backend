--liquibase formatted sql
--changeset mkonczal:42
ALTER TABLE categories ADD CONSTRAINT ui_categories_slug UNIQUE (slug);