--liquibase formatted sql
--changeset mkonczal:20
CREATE TABLE users
(
    id       BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(500) NOT NULL,
    enabled  BOOLEAN      NOT NULL
);

--changeset mkonczal:21
CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_username FOREIGN KEY (username) REFERENCES users (username)
);

--changeset mkonczal:22
CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

--changeset mkonczal:23
INSERT INTO users (id, username, password, enabled)
VALUES (1, 'admin', '{bcrypt}$2a$10$fbQeWktqHMjNnB6KJX0jeeCukMinoRobguhjiHHCKdo/Wjb4w8IXa', true);
INSERT INTO authorities(username, authority)
VALUES ('admin', 'ROLE_ADMIN');