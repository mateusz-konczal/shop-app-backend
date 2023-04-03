--liquibase formatted sql
--changeset mkonczal:9
ALTER TABLE reviews
    ADD moderated BOOLEAN DEFAULT FALSE AFTER content;