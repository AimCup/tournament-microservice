package xyz.aimcup.tournament.mapper.match;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import xyz.aimcup.generated.model.CreateMatchRequest;
import xyz.aimcup.generated.model.MatchResponseDto;
import xyz.aimcup.tournament.data.entity.match.Match;
import xyz.aimcup.tournament.data.entity.match.ParticipantBasedMatch;
import xyz.aimcup.tournament.data.entity.match.TeamBasedMatch;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MatchMapper {

    List<MatchResponseDto> toMatchResponseDtoListFrom(List<Match> tournaments);

    ParticipantBasedMatch toParticipantBasedMatchFrom(CreateMatchRequest createMatchRequest);

    TeamBasedMatch toTeamBasedMatchFrom(CreateMatchRequest createMatchRequest);

}
