package pl.webapp.shop.admin.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.admin.user.model.AdminUser;

import java.util.Optional;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {

    boolean existsByUsername(String username);

    Optional<AdminUser> findByUuid(String uuid);

    void deleteByUuid(String uuid);

    @Query("UPDATE AdminUser u SET u.enabled=TRUE WHERE u.id=:id")
    @Modifying
    void enableUserById(Long id);

    @Query("UPDATE AdminUser u SET u.enabled=FALSE WHERE u.id=:id")
    @Modifying
    void disableUserById(Long id);
}
