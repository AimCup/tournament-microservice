package xyz.aimcup.tournament.service.tournament;

import jakarta.persistence.DiscriminatorValue;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.stereotype.Component;
import xyz.aimcup.generated.model.TournamentCreateRequest;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament.TournamentType;
import xyz.aimcup.tournament.data.repository.tournament.TournamentRepository;

@Component
public final class TournamentServiceFacade implements TournamentBaseService,
    TournamentServiceDeterminant {

    private final Map<TournamentType, SpecificTournamentService> tournamentServices;
    private final TournamentRepository tournamentRepository;

    public TournamentServiceFacade(List<SpecificTournamentService> specificTournamentServices,
        TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentServices = specificTournamentServices.stream().collect(
            Collectors.toMap(
                SpecificTournamentService::getTournamentType,
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
    public SpecificTournamentService getTournamentService(UUID tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
            .orElseThrow(() -> new NotReadablePropertyException(
                SpecificTournamentService.class,
                "tournamentId",
                "Tournament with id " + tournamentId + " not found"));
        return getTournamentService(tournament);
    }

    @Override
    public SpecificTournamentService getTournamentService(Tournament tournament) {
        String tournamentTypeValue = tournament.getClass().getAnnotation(DiscriminatorValue.class)
            .value();
        if (tournamentTypeValue == null) {
            throw new NotReadablePropertyException(
                SpecificTournamentService.class,
                "tournamentTypeValue",
                "Tournament type is null");
        }
        TournamentType tournamentType = TournamentType.valueOf(tournamentTypeValue);
        return getTournamentService(tournamentType);
    }

    @Override
    public SpecificTournamentService getTournamentService(TournamentType tournamentType) {
        if (tournamentType == null) {
            throw new NotReadablePropertyException(
                SpecificTournamentService.class,
                "tournamentType",
                "Tournament type is null");
        }
        SpecificTournamentService specificTournamentService = tournamentServices.get(tournamentType);
        if (specificTournamentService == null) {
            throw new NotReadablePropertyException(
                SpecificTournamentService.class,
                "tournamentType",
                "Tournament type is not supported");
        }
        return specificTournamentService;
    }

    private TournamentType getTournamentType(String tournamentType) {
        return TournamentType.valueOf(tournamentType);
    }
}
