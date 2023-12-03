package xyz.aimcup.tournament.data.entity.tournament;


import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tournament_participants",
        joinColumns = @JoinColumn(name = "tournament_id"),
        inverseJoinColumns = @JoinColumn(name = "participant_id"))
    private Set<Participant> participants;
}
