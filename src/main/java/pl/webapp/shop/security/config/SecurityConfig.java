package pl.webapp.shop.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import pl.webapp.shop.security.filter.JwtAuthorizationFilter;
import pl.webapp.shop.security.model.UserRole;
import pl.webapp.shop.security.service.ShopUserDetailsService;

@Configuration
class SecurityConfig {

    private final String secret;

    SecurityConfig(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,
                                    AuthenticationManager authenticationManager,
                                    ShopUserDetailsService shopUserDetailsService) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/admin/**").hasRole(UserRole.ROLE_ADMIN.getRole())
                .requestMatchers("/actuator/**").hasRole(UserRole.ROLE_ADMIN.getRole())
                .requestMatchers(HttpMethod.GET, "/orders").authenticated()
                .requestMatchers(HttpMethod.POST, "/reviews").authenticated()
                .requestMatchers(HttpMethod.POST, "/newPassword").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/deleteAccount").authenticated()
                .anyRequest().permitAll()
        );
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(new JwtAuthorizationFilter(authenticationManager, shopUserDetailsService, secret));

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
