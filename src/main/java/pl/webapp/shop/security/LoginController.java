package pl.webapp.shop.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/login")
class LoginController {

    private final AuthenticationManager authenticationManager;
    private final long expirationTime;
    private final String secret;

    LoginController(AuthenticationManager authenticationManager,
                    @Value("${jwt.expirationTime}") long expirationTime,
                    @Value("${jwt.secret}") String secret) {
        this.authenticationManager = authenticationManager;
        this.expirationTime = expirationTime;
        this.secret = secret;
    }

    @PostMapping
    Token login(@RequestBody LoginCredentials loginCredentials) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginCredentials.username(), loginCredentials.password()));

        UserDetails principal = (UserDetails) authenticate.getPrincipal();

        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));

        return new Token(token);
    }

    private record LoginCredentials(String username, String password) {
    }

    private record Token(String token) {
    }
}
