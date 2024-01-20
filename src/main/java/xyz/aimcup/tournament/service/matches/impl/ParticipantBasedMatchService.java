package xyz.aimcup.tournament.service.matches.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.aimcup.generated.model.CreateMatchRequest;
import xyz.aimcup.tournament.data.entity.match.Match;
import xyz.aimcup.tournament.data.entity.match.MatchType;
import xyz.aimcup.tournament.data.repository.match.MatchRepository;
import xyz.aimcup.tournament.mapper.match.MatchMapper;
import xyz.aimcup.tournament.service.matches.SpecificMatchService;
import xyz.aimcup.tournament.service.matches.tools.SpecificMatchAssigner;

@Service
@RequiredArgsConstructor
public class ParticipantBasedMatchService implements SpecificMatchService {

    private final MatchMapper matchMapper;
    private final MatchRepository repository;
    private final SpecificMatchAssigner specificMatchAssigner;

    @Override
    public Match createMatch(CreateMatchRequest createMatchRequest) {
        final var matchToCreate = matchMapper.toParticipantBasedMatchFrom(createMatchRequest);
        specificMatchAssigner.assignQualificationGroupToMatch(matchToCreate);
        specificMatchAssigner.assignBracketPhaseToMatch(matchToCreate);
        specificMatchAssigner.assignParticipantsToMatch(matchToCreate,
            createMatchRequest.getParticipantsIds());
        return repository.save(matchToCreate);
    }

    @Override
    public boolean isMatchingService(MatchType matchType) {
        return MatchType.PARTICIPANT_VS.equals(matchType);
    }
}
