package pl.webapp.shop.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.shop.common.exception.NotIdenticalPasswordsException;
import pl.webapp.shop.security.dto.NewPasswordDto;
import pl.webapp.shop.security.model.User;
import pl.webapp.shop.security.repository.UserRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;

    @Transactional
    public void changePassword(NewPasswordDto newPasswordDto, String uuid) {
        if (!Objects.equals(newPasswordDto.password(), newPasswordDto.repeatedPassword())) {
            throw new NotIdenticalPasswordsException();
        }
        User user = userRepository.findByUuid(uuid).orElseThrow();
        user.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode(newPasswordDto.password()));
    }

    @Transactional
    public void deleteAccount(String uuid) {
        userRepository.deleteByUuid(uuid);
    }
}
