--liquibase formatted sql
--changeset mkonczal:37
ALTER TABLE reviews DROP FOREIGN KEY fk_reviews_user_uuid;