package xyz.aimcup.tournament.mapper.tournament;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import xyz.aimcup.generated.model.CreateTournamentRequest;
import xyz.aimcup.generated.model.SearchTournamentsDto;
import xyz.aimcup.generated.model.TournamentResponseDto;
import xyz.aimcup.generated.model.UpdateTournamentDataRequest;
import xyz.aimcup.tournament.data.entity.tournament.InternationalTournament;
import xyz.aimcup.tournament.data.entity.tournament.ParticipantBasedTournament;
import xyz.aimcup.tournament.data.entity.tournament.TeamBasedTournament;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TournamentMapper {

    TournamentResponseDto toTournamentResponseDtoFrom(Tournament tournament);

    List<SearchTournamentsDto> toTournamentResponseDtoListFrom(List<Tournament> tournaments);

    ParticipantBasedTournament toParticipantBasedTournamentFrom(
        CreateTournamentRequest createTournamentRequest);

    InternationalTournament toInternationalTournamentFrom(
        CreateTournamentRequest createTournamentRequest);

    TeamBasedTournament toTeamBasedTournamentFrom(CreateTournamentRequest createTournamentRequest);

    @Mapping(target = "tournamentData.teamSize", source = "teamSize",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "tournamentData.maxTeamSize", source = "maxTeamSize",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "tournamentData.minimumRankLimit", source = "minimumRankLimit",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "tournamentData.maximumRankLimit", source = "maximumRankLimit",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "tournamentData.participantsPerQualificationSpotLimit",
        source = "participantsPerQualificationSpotLimit",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTournamentWith(@MappingTarget Tournament tournament,
        UpdateTournamentDataRequest updateTournamentRequest);
}
