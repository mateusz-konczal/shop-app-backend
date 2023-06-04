--liquibase formatted sql
--changeset mkonczal:26
ALTER TABLE reviews
    ADD user_uuid VARCHAR(36) NOT NULL AFTER moderated;

--changeset mkonczal:27
ALTER TABLE reviews
    ADD CONSTRAINT fk_reviews_user_uuid FOREIGN KEY (user_uuid) REFERENCES users (uuid);