package xyz.aimcup.tournament.service.tournament;

import org.springframework.stereotype.Service;
import xyz.aimcup.tournament.data.entity.tournament.TournamentType;

@Service
public interface SpecificTournamentService extends TournamentBaseService {
    TournamentType getTournamentType();

}
