package pl.webapp.shop.admin.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.admin.common.model.AdminProduct;

@Repository
public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {

    Page<AdminProduct> findAllBySalePriceIsNotNull(Pageable pageable);

    @Query("UPDATE AdminProduct p SET p.enabled = TRUE WHERE p.id = :id")
    @Modifying
    void enableProductById(Long id);

    @Query("UPDATE AdminProduct p SET p.enabled = FALSE WHERE p.id = :id")
    @Modifying
    void disableProductById(Long id);
}
