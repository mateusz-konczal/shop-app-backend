package pl.webapp.shop.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.common.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByEnabledIsTrue(Pageable pageable);

    Page<Product> findAll(Specification<Product> specification, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.enabled ORDER BY COALESCE(p.salePrice, p.price) ASC")
    Page<Product> findAllOrderByEndPriceAsc(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.enabled ORDER BY COALESCE(p.salePrice, p.price) DESC")
    Page<Product> findAllOrderByEndPriceDesc(Pageable pageable);

    Optional<Product> findBySlug(String slug);

    Page<Product> findAllByCategoryIdAndEnabledIsTrue(Long categoryId, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.categoryId = :categoryId AND p.enabled ORDER BY COALESCE(p.salePrice, p.price) ASC")
    Page<Product> findAllByCategoryIdOrderByEndPriceAsc(Long categoryId, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.categoryId = :categoryId AND p.enabled ORDER BY COALESCE(p.salePrice, p.price) DESC")
    Page<Product> findAllByCategoryIdOrderByEndPriceDesc(Long categoryId, Pageable pageable);

    List<Product> findTop10BySalePriceIsNotNullAndEnabledIsTrue();

    List<Product> findTop10BySalePriceIsNotNullAndEnabledIsTrue(Sort sort);
}
