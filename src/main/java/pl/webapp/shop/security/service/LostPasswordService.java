package pl.webapp.shop.security.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.shop.common.mail.MailClientService;
import pl.webapp.shop.security.dto.EmailDto;
import pl.webapp.shop.security.dto.NewPasswordDto;
import pl.webapp.shop.security.model.User;
import pl.webapp.shop.security.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Objects;

import static pl.webapp.shop.security.service.mapper.LostPasswordMailContentMapper.createPasswordMailContent;

@Service
@RequiredArgsConstructor
public class LostPasswordService {

    @Value("${app.serviceAddress}")
    private String serviceAddress;
    private final UserRepository userRepository;
    private final MailClientService mailClientService;

    @Transactional
    public void sendLostPasswordLink(EmailDto emailDto) {
        String email = emailDto.email();
        User user = userRepository.findByUsername(email)
                .orElseThrow(() -> new IllegalArgumentException("Podany adres e-mail nie istnieje"));
        String hash = generateHashForLostPassword(user);
        user.setHash(hash);
        user.setHashDateTime(LocalDateTime.now());
        mailClientService.getInstance()
                .send(email, "Resetowanie hasła", createPasswordMailContent(email, createLink(hash)));
    }

    @Transactional
    public void changePassword(NewPasswordDto newPasswordDto) {
        if (!Objects.equals(newPasswordDto.password(), newPasswordDto.repeatedPassword())) {
            throw new IllegalArgumentException("Hasła nie są identyczne");
        }
        User user = userRepository.findByHash(newPasswordDto.hash())
                .orElseThrow(() -> new IllegalArgumentException("Link do zmiany hasła jest nieprawidłowy"));
        if (user.getHashDateTime().plusMinutes(10).isAfter(LocalDateTime.now())) {
            user.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode(newPasswordDto.password()));
            user.setHash(null);
            user.setHashDateTime(null);
        } else {
            throw new IllegalArgumentException("Link do zmiany hasła stracił ważność");
        }
    }

    private String generateHashForLostPassword(User user) {
        String toHash = user.getUuid() + user.getUsername() + user.getPassword() + LocalDateTime.now();
        return DigestUtils.sha256Hex(toHash);
    }

    private String createLink(String hash) {
        return serviceAddress + "/lostPassword/" + hash;
    }
}
