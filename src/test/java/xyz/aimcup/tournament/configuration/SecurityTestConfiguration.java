package xyz.aimcup.tournament.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@EnableWebSecurity
@ActiveProfiles("test")
public class SecurityTestConfiguration {

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .sessionManagement(Customizer.withDefaults());
        httpSecurity
            .authorizeHttpRequests(Customizer.withDefaults())
            .authorizeHttpRequests(authorize -> {
                authorize
                    .requestMatchers("/keycloak-test", "/tournament/keycloak-test")
                    .fullyAuthenticated()
                    .anyRequest().permitAll();
            });
        return httpSecurity.build();
    }
}
