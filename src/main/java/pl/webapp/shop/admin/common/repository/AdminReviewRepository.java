package pl.webapp.shop.admin.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.admin.review.model.AdminReview;

@Repository
public interface AdminReviewRepository extends JpaRepository<AdminReview, Long> {

    @Query("UPDATE AdminReview r SET r.moderated = TRUE WHERE r.id = :id")
    @Modifying
    void moderateById(Long id);

    @Query("DELETE FROM AdminReview r WHERE r.productId = :productId")
    @Modifying
    void deleteAllByProductId(Long productId);
}
