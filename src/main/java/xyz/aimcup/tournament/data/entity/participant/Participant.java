package xyz.aimcup.tournament.data.entity.participant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.aimcup.tournament.data.entity.match.Match;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Participant {

    @Id
    private UUID id;

    @Column(name = "participant_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ParticipantType participantType;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "participants")
    private Set<Match> matches;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "participants")
    private Set<Tournament> tournaments;

}
