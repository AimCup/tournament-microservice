package xyz.aimcup.tournament.service.tournament.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.aimcup.generated.model.CreateTournamentRequest;
import xyz.aimcup.tournament.data.entity.tournament.ParticipantBasedTournament;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament.TournamentType;
import xyz.aimcup.tournament.mapper.tournament.TournamentMapper;
import xyz.aimcup.tournament.service.tournament.ParticipantBasedTournamentService;

@Service
@RequiredArgsConstructor
public class ParticipantBasedTournamentServiceImpl implements ParticipantBasedTournamentService {

    private final TournamentMapper tournamentMapper;

    @Override
    public TournamentType getTournamentType() {
        return TournamentType.PARTICIPANT_VS;
    }

    @Override
    public Tournament createTournament(CreateTournamentRequest createTournamentRequest) {
        ParticipantBasedTournament tournament =
            tournamentMapper.toParticipantBasedTournamentFrom(createTournamentRequest);
        tournament.setCreatedBy(UUID.randomUUID());  //TODO: Add real user (task: AC-59)
        return tournament;
    }
}
