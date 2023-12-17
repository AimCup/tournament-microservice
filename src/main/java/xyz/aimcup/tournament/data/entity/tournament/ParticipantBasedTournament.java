package xyz.aimcup.tournament.data.entity.tournament;


import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import xyz.aimcup.tournament.data.entity.participant.Participant;
import xyz.aimcup.tournament.data.entity.tournament.TournamentType.TournamentTypeNames;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@DiscriminatorValue(TournamentTypeNames.PARTICIPANT_VS)
public final class ParticipantBasedTournament extends Tournament {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournament")
    private Set<Participant> participants;

    @Override
    public Integer calculateNumberOfQualificationSpots() {
        return participants.size() / getTournamentData().getParticipantsPerQualificationSpotLimit() + 1;
    }
}
