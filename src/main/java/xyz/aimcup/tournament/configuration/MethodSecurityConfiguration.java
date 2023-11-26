package xyz.aimcup.tournament.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import xyz.aimcup.tournament.security.TournamentRolePermissionEvaluator;
import xyz.aimcup.tournament.service.tournament.TournamentService;

@Configuration
@RequiredArgsConstructor
public class MethodSecurityConfiguration {
    private final TournamentService tournamentService;

    @Bean
    protected MethodSecurityExpressionHandler tournamentRoleExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(new TournamentRolePermissionEvaluator(tournamentService));
        return expressionHandler;
    }
}
