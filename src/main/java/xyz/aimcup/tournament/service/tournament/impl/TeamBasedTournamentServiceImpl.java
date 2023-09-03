package xyz.aimcup.tournament.service.tournament.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.aimcup.generated.model.TournamentCreateRequest;
import xyz.aimcup.tournament.data.entity.tournament.TeamBasedTournament;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament.TournamentType;
import xyz.aimcup.tournament.data.repository.tournament.TournamentRepository;
import xyz.aimcup.tournament.service.tournament.ITeamBasedTournamentService;

@Service
@RequiredArgsConstructor
public class TeamBasedTournamentServiceImpl implements ITeamBasedTournamentService {
    private final TournamentRepository tournamentRepository;

    @Override
    public TournamentType getTournamentType() {
        return TournamentType.TEAM_VS;
    }

    @Override
    public Tournament createTournament(TournamentCreateRequest tournamentCreateRequest) {
        TeamBasedTournament tournament = TeamBasedTournament.builder()
            .name(tournamentCreateRequest.getName())
            .abbreviation(tournamentCreateRequest.getAbbreviation())
            .createdBy(UUID.randomUUID())
            .build();
        return tournamentRepository.save(tournament);
    }
}
