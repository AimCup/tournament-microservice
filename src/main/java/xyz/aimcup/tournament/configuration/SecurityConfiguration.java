package xyz.aimcup.tournament.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .oauth2Client(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults());
        httpSecurity
                .sessionManagement(Customizer.withDefaults());
        httpSecurity.csrf(AbstractHttpConfigurer::disable); // Unable to make POST request without this
        httpSecurity
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
        return httpSecurity.build();
    }

}
