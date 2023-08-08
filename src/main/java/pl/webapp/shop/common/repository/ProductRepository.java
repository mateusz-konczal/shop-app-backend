package pl.webapp.shop.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.common.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByEnabledIsTrue(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.enabled ORDER BY COALESCE(p.salePrice, p.price) ASC")
    Page<Product> findAllByEnabledIsTrueOrderByCoalesceAsc(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.enabled ORDER BY COALESCE(p.salePrice, p.price) DESC")
    Page<Product> findAllByEnabledIsTrueOrderByCoalesceDesc(Pageable pageable);

    Optional<Product> findBySlug(String slug);

    Page<Product> findAllByCategoryIdAndEnabledIsTrue(Long categoryId, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.categoryId = :categoryId AND p.enabled ORDER BY COALESCE(p.salePrice, p.price) ASC")
    Page<Product> findAllByCategoryIdAndEnabledIsTrueOrderByCoalesceAsc(Long categoryId, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.categoryId = :categoryId AND p.enabled ORDER BY COALESCE(p.salePrice, p.price) DESC")
    Page<Product> findAllByCategoryIdAndEnabledIsTrueOrderByCoalesceDesc(Long categoryId, Pageable pageable);

    List<Product> findTop10BySalePriceIsNotNullAndEnabledIsTrue();

    List<Product> findTop10BySalePriceIsNotNullAndEnabledIsTrueOrderByNameAsc();

    List<Product> findTop10BySalePriceIsNotNullAndEnabledIsTrueOrderByNameDesc();

    List<Product> findTop10BySalePriceIsNotNullAndEnabledIsTrueOrderBySalePriceAsc();

    List<Product> findTop10BySalePriceIsNotNullAndEnabledIsTrueOrderBySalePriceDesc();
}
