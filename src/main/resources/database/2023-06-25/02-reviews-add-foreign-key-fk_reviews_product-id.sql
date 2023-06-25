--liquibase formatted sql
--changeset mkonczal:38
ALTER TABLE reviews
    ADD CONSTRAINT fk_reviews_product_id FOREIGN KEY (product_id) REFERENCES products (id);