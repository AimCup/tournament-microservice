package xyz.aimcup.tournament.mapper.tournament;

import java.util.List;
import org.mapstruct.Mapper;
import xyz.aimcup.generated.model.TournamentResponseDto;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;

@Mapper(componentModel = "spring")
public interface TournamentMapper {
    TournamentResponseDto toTournamentResponseDto(Tournament tournament);

    List<TournamentResponseDto> toTournamentResponseDtos(List<Tournament> tournaments);
}
