package xyz.aimcup.tournament.service.tournament;

import jakarta.persistence.DiscriminatorValue;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.stereotype.Component;
import xyz.aimcup.generated.model.TournamentCreateRequest;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament.TournamentType;
import xyz.aimcup.tournament.data.repository.tournament.TournamentRepository;

@Component
public final class TournamentServiceFacade implements ITournamentBaseService,
    ITournamentServiceDeterminant {

    private final Map<TournamentType, ITournamentService> tournamentServices;
    private final TournamentRepository tournamentRepository;

    public TournamentServiceFacade(List<ITournamentService> tournamentServices,
        TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentServices = tournamentServices.stream().collect(
            Collectors.toMap(
                ITournamentService::getTournamentType,
                Function.identity()
            )
        );
    }

    @Override
    public Tournament createTournament(TournamentCreateRequest tournamentCreateRequest) {
        TournamentType tournamentType = this.getTournamentType(
            tournamentCreateRequest.getType().getValue());
        return getTournamentService(tournamentType)
            .createTournament(tournamentCreateRequest);
    }

    @Override
    public ITournamentService getTournamentService(UUID tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
            .orElseThrow(() -> new NotReadablePropertyException(
                ITournamentService.class,
                "tournamentId",
                "Tournament with id " + tournamentId + " not found"));
        return getTournamentService(tournament);
    }

    @Override
    public ITournamentService getTournamentService(Tournament tournament) {
        String tournamentTypeValue = tournament.getClass().getAnnotation(DiscriminatorValue.class)
            .value();
        if (tournamentTypeValue == null) {
            throw new NotReadablePropertyException(
                ITournamentService.class,
                "tournamentTypeValue",
                "Tournament type is null");
        }
        TournamentType tournamentType = TournamentType.valueOf(tournamentTypeValue);
        return getTournamentService(tournamentType);
    }

    @Override
    public ITournamentService getTournamentService(TournamentType tournamentType) {
        if (tournamentType == null) {
            throw new NotReadablePropertyException(
                ITournamentService.class,
                "tournamentType",
                "Tournament type is null");
        }
        ITournamentService tournamentService = tournamentServices.get(tournamentType);
        if (tournamentService == null) {
            throw new NotReadablePropertyException(
                ITournamentService.class,
                "tournamentType",
                "Tournament type is not supported");
        }
        return tournamentService;
    }

    private TournamentType getTournamentType(String tournamentType) {
        return TournamentType.valueOf(tournamentType);
    }
}
