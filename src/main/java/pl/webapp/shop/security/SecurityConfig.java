package pl.webapp.shop.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import pl.webapp.shop.security.model.UserRole;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = true)
class SecurityConfig {

    private final String secret;

    SecurityConfig(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,
                                    AuthenticationManager authenticationManager,
                                    UserDetailsService userDetailsService) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/v1/admin/**").hasRole(UserRole.ROLE_ADMIN.getRole())
                .anyRequest().permitAll()
        );
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(new JwtAuthorizationFilter(authenticationManager, userDetailsService, secret));

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
}
