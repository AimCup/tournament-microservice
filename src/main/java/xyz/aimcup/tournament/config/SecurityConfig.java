//package xyz.aimcup.tournament.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.web.SecurityFilterChain;
//
//public class SecurityConfig {
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .cors(AbstractHttpConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/")
//                            .permitAll()
//                            .anyRequest()
//                            .authenticated();
//                })
//                .oauth2Login(oauth2 -> oauth2
//                        .userInfoEndpoint(userInfo -> {
//                            userInfo.userService(oAuth2UserService);
//                        })
//                )
////                .exceptionHandling(e -> e.authenticationEntryPoint(new Http403ForbiddenEntryPoint()))
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .formLogin(AbstractHttpConfigurer::disable)
//                .build();
//    }
//}
