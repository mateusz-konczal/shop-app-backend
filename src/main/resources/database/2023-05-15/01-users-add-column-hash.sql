--liquibase formatted sql
--changeset mkonczal:28
ALTER TABLE users ADD hash VARCHAR(120) AFTER enabled;

--changeset mkonczal:29
ALTER TABLE users ADD hash_date_time DATETIME AFTER hash;