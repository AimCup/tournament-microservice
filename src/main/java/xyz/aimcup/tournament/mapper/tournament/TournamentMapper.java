package xyz.aimcup.tournament.mapper.tournament;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import xyz.aimcup.generated.model.CreateTournamentRequest;
import xyz.aimcup.generated.model.TournamentResponseDto;
import xyz.aimcup.generated.model.UpdateTournamentRequest;
import xyz.aimcup.tournament.data.entity.tournament.InternationalTournament;
import xyz.aimcup.tournament.data.entity.tournament.ParticipantBasedTournament;
import xyz.aimcup.tournament.data.entity.tournament.TeamBasedTournament;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TournamentMapper {

    TournamentResponseDto toTournamentResponseDtoFrom(Tournament tournament);

    List<TournamentResponseDto> toTournamentResponseDtoListFrom(List<Tournament> tournaments);

    ParticipantBasedTournament toParticipantBasedTournamentFrom(
        CreateTournamentRequest createTournamentRequest);

    InternationalTournament toInternationalTournamentFrom(
        CreateTournamentRequest createTournamentRequest);

    TeamBasedTournament toTeamBasedTournamentFrom(CreateTournamentRequest createTournamentRequest);
    void updateTournament(@MappingTarget Tournament tournament,UpdateTournamentRequest updateTournamentRequest);
}
