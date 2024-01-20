package xyz.aimcup.tournament.data.entity.participant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.aimcup.tournament.data.entity.match.ParticipantBasedMatch;
import xyz.aimcup.tournament.data.entity.qualification.ParticipantBasedQualificationRoom;
import xyz.aimcup.tournament.data.entity.team.Team;
import xyz.aimcup.tournament.data.entity.tournament.ParticipantBasedTournament;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tournament_participants",
        joinColumns = @JoinColumn(name = "tournament_id"),
        inverseJoinColumns = @JoinColumn(name = "participant_id"))
    private ParticipantBasedTournament tournament;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "participants")
    private Set<ParticipantBasedMatch> matches;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "qualification_room_id", referencedColumnName = "id")
    private ParticipantBasedQualificationRoom qualificationRoom;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

}
