package pl.webapp.shop.security.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.shop.common.exception.NotIdenticalPasswordsException;
import pl.webapp.shop.common.mail.MailClientService;
import pl.webapp.shop.security.dto.EmailDto;
import pl.webapp.shop.security.dto.ResetPasswordDto;
import pl.webapp.shop.security.exception.EmailNotFoundException;
import pl.webapp.shop.security.exception.ExpiredLinkException;
import pl.webapp.shop.security.exception.LinkNotFoundException;
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
        User user = userRepository.findByUsername(email).orElseThrow(EmailNotFoundException::new);
        String hash = generateHashForLostPassword(user);
        user.setHash(hash);
        user.setHashDateTime(LocalDateTime.now());
        mailClientService.getInstance()
                .send(email, "Resetowanie hasła", createPasswordMailContent(email, createLink(hash)));
    }

    @Transactional
    public void changePassword(ResetPasswordDto resetPasswordDto) {
        if (!Objects.equals(resetPasswordDto.password(), resetPasswordDto.repeatedPassword())) {
            throw new NotIdenticalPasswordsException();
        }
        User user = userRepository.findByHash(resetPasswordDto.hash()).orElseThrow(LinkNotFoundException::new);
        if (user.getHashDateTime().plusMinutes(10).isAfter(LocalDateTime.now())) {
            user.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode(resetPasswordDto.password()));
            user.setHash(null);
            user.setHashDateTime(null);
        } else {
            throw new ExpiredLinkException();
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
