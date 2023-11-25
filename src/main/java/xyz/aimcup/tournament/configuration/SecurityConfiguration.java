package xyz.aimcup.tournament.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration {

    private final ClientRegistrationRepository clientRegistrationRepository;

    public SecurityConfiguration(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        ClientRegistration clientRegistration = this.clientRegistrationRepository.findByRegistrationId("aimcup");
        log.info("clientRegistration: {}", clientRegistration);
        httpSecurity
                .oauth2Client(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults());
        httpSecurity
                .sessionManagement(Customizer.withDefaults());
        httpSecurity
                .authorizeHttpRequests(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers("/keycloak-test", "/tournament/keycloak-test").fullyAuthenticated()
                            .anyRequest().permitAll();
                });
        return httpSecurity.build();
    }
}
