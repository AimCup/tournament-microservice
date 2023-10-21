package xyz.aimcup.tournament.service.matches.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.aimcup.generated.model.MatchResponseDto;
import xyz.aimcup.generated.model.SearchMatchRequest;
import xyz.aimcup.tournament.data.entity.match.Match;
import xyz.aimcup.tournament.data.repository.match.MatchRepository;
import xyz.aimcup.tournament.data.repository.match.MatchSpecification;
import xyz.aimcup.tournament.mapper.match.MatchMapper;
import xyz.aimcup.tournament.service.matches.MatchBaseService;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchBaseService {

    private final MatchRepository repository;
    private final MatchMapper matchMapper;


    @Override
    public List<MatchResponseDto> getMatches(SearchMatchRequest searchMatchRequest) {
        Specification<Match> searchSpecification = MatchSpecification
            .createSearchSpecification(searchMatchRequest);
        final var matchesFound = repository.findAll(searchSpecification);

        return matchMapper.toMatchResponseDtoListFrom(matchesFound);
    }
}
