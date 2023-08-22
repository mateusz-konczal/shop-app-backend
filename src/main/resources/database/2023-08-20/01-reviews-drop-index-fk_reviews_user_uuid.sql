--liquibase formatted sql
--changeset mkonczal:45
ALTER TABLE reviews DROP INDEX fk_reviews_user_uuid;