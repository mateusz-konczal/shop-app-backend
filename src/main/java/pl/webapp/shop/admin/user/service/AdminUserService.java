package pl.webapp.shop.admin.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.shop.admin.user.model.AdminUser;
import pl.webapp.shop.admin.user.repository.AdminUserRepository;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final AdminUserRepository userRepository;

    public Page<AdminUser> getUsers(Pageable pageable) {
        return userRepository.findAll(PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("authorities", "username").ascending()));
    }

    public boolean isUserExist(String username) {
        return userRepository.existsByUsername(username);
    }

    public void createUser(AdminUser user) {
        userRepository.save(user);
    }

    @Transactional
    public void enableUser(Long id) {
        userRepository.enableUserById(id);
    }

    @Transactional
    public void disableUser(Long id) {
        userRepository.disableUserById(id);
    }
}
