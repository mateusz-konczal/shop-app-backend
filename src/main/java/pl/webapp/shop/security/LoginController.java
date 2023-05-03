package pl.webapp.shop.security;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
class LoginController {

    @PostMapping()
    String login(@RequestBody LoginCredentials loginCredentials) {
        return null;
    }

    private record LoginCredentials(

            String username,
            String password) {
    }
}
