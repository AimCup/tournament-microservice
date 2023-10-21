package xyz.aimcup.tournament.data.repository.match;

import java.util.Objects;
import java.util.UUID;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import xyz.aimcup.generated.model.SearchMatchRequest;
import xyz.aimcup.tournament.data.entity.match.Match;
import xyz.aimcup.tournament.data.entity.match.MatchType;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class MatchSpecification {

    public static Specification<Match> createSearchSpecification(SearchMatchRequest searchMatchRequest) {
        Specification<Match> tournamentIdSpec = MatchSpecification.tournamentIdEquals(searchMatchRequest.getTournamentId());
        Specification<Match> participantIdSpec = MatchSpecification.participantIdEquals(searchMatchRequest.getParticipantIds());
        Specification<Match> bracketsPhaseIdSpec = MatchSpecification.bracketsPhaseIdEquals(searchMatchRequest.getBracketsPhaseId());
        Specification<Match> qualificationGroupIdSpec = MatchSpecification.qualificationGroupIdEquals(searchMatchRequest.getQualificationGroupId());
        Specification<Match> matchTypeSpec = MatchSpecification.matchTypeEquals(
            Objects.nonNull(searchMatchRequest.getMatchType()) ? MatchType.valueOf(searchMatchRequest.getMatchType().getValue()) : null);

        return Specification.allOf(tournamentIdSpec, participantIdSpec, bracketsPhaseIdSpec, qualificationGroupIdSpec, matchTypeSpec);
    }

    public static Specification<Match> tournamentIdEquals(UUID tournamentId) {
        return (root, query, builder) ->
            tournamentId == null ?
                builder.conjunction() :
                builder.equal(root.get("tournamentId"), tournamentId);
    }

    public static Specification<Match> participantIdEquals(UUID participantId) {
        return (root, query, builder) ->
            participantId == null ?
                builder.conjunction() :
                builder.equal(root.get("participants").get("id"), participantId);
    }

    public static Specification<Match> bracketsPhaseIdEquals(UUID bracketsPhaseId) {
        return (root, query, builder) ->
            bracketsPhaseId == null ?
                builder.conjunction() :
                builder.equal(root.get("bracketsPhaseId"), bracketsPhaseId);
    }

    public static Specification<Match> qualificationGroupIdEquals(UUID qualificationGroupId) {
        return (root, query, builder) ->
            qualificationGroupId == null ?
                builder.conjunction() :
                builder.equal(root.get("qualificationGroupId"), qualificationGroupId);
    }

    public static Specification<Match> matchTypeEquals(MatchType matchType) {
        return (root, query, builder) ->
            matchType == null ?
                builder.conjunction() :
                builder.equal(root.get("matchType"), matchType);
    }

}
