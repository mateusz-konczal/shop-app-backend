package pl.webapp.shop.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.category.model.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("""
            SELECT c FROM Category c
            LEFT JOIN FETCH c.products
            WHERE c.slug=:slug
            """)
    Optional<Category> findBySlug(String slug);
}
