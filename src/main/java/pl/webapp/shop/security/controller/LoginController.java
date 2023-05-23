package pl.webapp.shop.security.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.security.model.ShopUserDetails;
import pl.webapp.shop.security.model.UserRole;
import pl.webapp.shop.security.service.UserService;

import java.util.Date;
import java.util.Objects;

import static pl.webapp.shop.ShopApplication.PASSWORD_REGEX;

@RestController
class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final long expirationTime;
    private final String secret;

    LoginController(AuthenticationManager authenticationManager,
                    UserService userService,
                    @Value("${jwt.expirationTime}") long expirationTime,
                    @Value("${jwt.secret}") String secret) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.expirationTime = expirationTime;
        this.secret = secret;
    }

    @PostMapping("/login")
    Token login(@RequestBody LoginCredentials loginCredentials) {
        return authenticate(loginCredentials.username(), loginCredentials.password());
    }

    @PostMapping("/register")
    Token register(@RequestBody @Valid RegisterCredentials registerCredentials) {
        if (!Objects.equals(registerCredentials.password(), registerCredentials.repeatedPassword())) {
            throw new IllegalArgumentException("Hasła nie są identyczne");
        }
        if (userService.isUserExist(registerCredentials.username())) {
            throw new IllegalArgumentException("Podana nazwa użytkownika już istnieje");
        }
        userService.createUser(registerCredentials.username(), registerCredentials.password());

        return authenticate(registerCredentials.username(), registerCredentials.password());
    }

    private Token authenticate(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            return createJwtToken((ShopUserDetails) authentication.getPrincipal());
        } catch (DisabledException e) {
            throw new IllegalArgumentException("Twoje konto zostało wyłączone, skontaktuj się z administratorem sklepu");
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException("Podaj poprawne dane logowania");
        }
    }

    private Token createJwtToken(ShopUserDetails principal) {
        String token = JWT.create()
                .withSubject(principal.getUuid())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));

        return new Token(token, principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(role -> UserRole.ROLE_ADMIN.name().equals(role))
                .map(role -> true)
                .findFirst()
                .orElse(false));
    }

    private record LoginCredentials(String username, String password) {
    }

    private record RegisterCredentials(
            @Email
            String username,
            @Pattern(regexp = PASSWORD_REGEX)
            String password,
            @NotBlank
            String repeatedPassword) {
    }

    private record Token(String token, boolean adminAccess) {
    }
}
