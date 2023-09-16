package xyz.aimcup.tournament.service.tournament;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.aimcup.generated.model.CreateTournamentRequest;
import xyz.aimcup.tournament.data.entity.qualification.QualificationType;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament.TournamentType;
import xyz.aimcup.tournament.data.repository.tournament.TournamentRepository;
import xyz.aimcup.tournament.service.qualifications.impl.QualificationServiceFacade;

@Component
public class TournamentServiceFacade implements TournamentBaseService,
    TournamentServiceDeterminant {

    private final Map<TournamentType, SpecificTournamentService> tournamentServices;
    private final TournamentRepository tournamentRepository;
    private final QualificationServiceFacade qualificationServiceFacade;

    public TournamentServiceFacade(List<SpecificTournamentService> specificTournamentServices,
        TournamentRepository tournamentRepository,
        QualificationServiceFacade qualificationServiceFacade) {
        this.tournamentRepository = tournamentRepository;
        this.qualificationServiceFacade = qualificationServiceFacade;
        this.tournamentServices = specificTournamentServices.stream().collect(
            Collectors.toMap(
                SpecificTournamentService::getTournamentType,
                Function.identity()
            )
        );
    }

    @Override
    @Transactional
    public Tournament createTournament(CreateTournamentRequest createTournamentRequest) {
        TournamentType tournamentType = this.getTournamentType(
            createTournamentRequest.getTournamentType().getValue());
        var createdTournament = getTournamentService(tournamentType)
            .createTournament(createTournamentRequest);
        prepareQualificationsFor(createdTournament);
        return tournamentRepository.save(createdTournament);
    }

    @Override
    public SpecificTournamentService getTournamentService(TournamentType tournamentType) {
        if (Objects.isNull(tournamentType)) {
            throw new NotReadablePropertyException(
                SpecificTournamentService.class,
                "tournamentType",
                "Tournament type is null");
        }
        SpecificTournamentService specificTournamentService = tournamentServices
            .get(tournamentType);
        if (Objects.isNull(specificTournamentService)) {
            throw new NotReadablePropertyException(
                SpecificTournamentService.class,
                "tournamentType",
                "Tournament type is not supported");
        }
        return specificTournamentService;
    }

    private void prepareQualificationsFor(Tournament tournament) {
        QualificationType qualificationType = tournament.getQualificationType();

        qualificationServiceFacade.getQualificationService(qualificationType)
            .createQualificationsFor(tournament);
    }

    private TournamentType getTournamentType(String tournamentType) {
        return TournamentType.valueOf(tournamentType);
    }
}
