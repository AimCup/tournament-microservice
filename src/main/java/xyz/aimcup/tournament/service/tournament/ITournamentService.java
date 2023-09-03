package xyz.aimcup.tournament.service.tournament;

import org.springframework.stereotype.Service;
import xyz.aimcup.generated.model.TournamentCreateRequest;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament.TournamentType;

@Service
public interface ITournamentService extends ITournamentBaseService {
    TournamentType getTournamentType();

}
