package xyz.aimcup.tournament.mapper.example;

import org.mapstruct.Mapper;
import xyz.aimcup.generated.model.TournamentCreateRequest;
import xyz.aimcup.tournament.data.entity.tournament.PlayerBasedTournament;

@Mapper(componentModel = "spring")
public interface ExampleMapper {
    PlayerBasedTournament tournamentCreateRequestToPlayerBasedTournament(
        TournamentCreateRequest tournamentCreateRequest);
}
