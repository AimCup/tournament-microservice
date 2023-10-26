package xyz.aimcup.tournament.service.matches.tools;

import static xyz.aimcup.tournament.data.entity.phase.TournamentPhaseType.BRACKETS;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.aimcup.tournament.data.entity.match.Match;
import xyz.aimcup.tournament.data.entity.participant.Participant;
import xyz.aimcup.tournament.data.repository.participants.ParticipantRepository;
import xyz.aimcup.tournament.data.repository.phases.BracketsPhaseRepository;
import xyz.aimcup.tournament.data.repository.qualification.QualificationGroupRepository;
import xyz.aimcup.tournament.service.participants.exceptions.ParticipantNotAssignedException;
import xyz.aimcup.tournament.service.phases.exceptions.PhaseNotFoundException;
import xyz.aimcup.tournament.service.qualifications.exceptions.QualificationGroupNotFoundException;

@Component
@RequiredArgsConstructor
public class SpecificMatchAssigner {

    private final QualificationGroupRepository qualificationGroupRepository;
    private final BracketsPhaseRepository bracketsPhaseRepository;
    private final ParticipantRepository participantRepository;

    public void assignQualificationGroupToMatch(Match match) {
        if (Objects.nonNull(match.getQualificationGroupId())) {
            final var qualificationGroup = qualificationGroupRepository
                .findById(match.getQualificationGroupId())
                .orElseThrow(
                    () -> new QualificationGroupNotFoundException(match.getQualificationGroupId()));
            match.setQualificationGroup(qualificationGroup);
        }
    }

    public void assignBracketPhaseToMatch(Match match) {
        if (Objects.nonNull(match.getBracketsPhaseId())) {
            final var bracketsPhase = bracketsPhaseRepository
                .findById(match.getBracketsPhaseId())
                .orElseThrow(
                    () -> new PhaseNotFoundException(BRACKETS, match.getBracketsPhaseId()));
            match.setBracketsPhase(bracketsPhase);
        }
    }

    public void assignParticipantsToMatch(Match match, Set<UUID> participantsIds) {
        final var numberOfParticipantsToFind = participantsIds.size();
        final var participantsFound = participantRepository
            .findAllByIdInAndTournaments_Id(participantsIds, match.getTournamentId());
        if (participantsFound.size() != numberOfParticipantsToFind) {
            throw new ParticipantNotAssignedException(
                "TOURNAMENT: %s".formatted(match.getTournamentId()),
                new TreeSet<>(participantsIds),
                participantsFound.stream()
                    .map(Participant::getId)
                    .collect(Collectors.toCollection(TreeSet::new)));
        }
        match.setParticipants(participantsFound);
    }

}
