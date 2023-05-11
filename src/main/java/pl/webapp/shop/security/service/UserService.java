package pl.webapp.shop.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.webapp.shop.security.model.User;
import pl.webapp.shop.security.model.UserRole;
import pl.webapp.shop.security.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean isUserExist(String username) {
        return userRepository.existsByUsername(username);
    }

    public void createUser(String username, String password) {
        userRepository.save(User.builder()
                .uuid(UUID.randomUUID().toString())
                .username(username)
                .password("{bcrypt}" + new BCryptPasswordEncoder().encode(password))
                .enabled(true)
                .authorities(List.of(UserRole.ROLE_CUSTOMER))
                .build());
    }
}
