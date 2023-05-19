package pl.webapp.shop.admin.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.shop.admin.user.dto.AdminNewPasswordDto;
import pl.webapp.shop.admin.user.model.AdminUser;
import pl.webapp.shop.admin.user.repository.AdminUserRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdminAccountService {

    private final AdminUserRepository userRepository;

    @Transactional
    public void changePassword(AdminNewPasswordDto adminNewPasswordDto, String uuid) {
        if (!Objects.equals(adminNewPasswordDto.password(), adminNewPasswordDto.repeatedPassword())) {
            throw new IllegalArgumentException("Hasła nie są identyczne");
        }
        AdminUser user = userRepository.findByUuid(uuid).orElseThrow();
        user.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode(adminNewPasswordDto.password()));
    }

    @Transactional
    public void deleteAccount(String uuid) {
        userRepository.deleteByUuid(uuid);
    }
}
