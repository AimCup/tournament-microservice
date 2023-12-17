package xyz.aimcup.tournament.data.entity.team;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.aimcup.tournament.data.entity.match.TeamBasedMatch;
import xyz.aimcup.tournament.data.entity.participant.Participant;
import xyz.aimcup.tournament.data.entity.qualification.TeamBasedQualificationRoom;
import xyz.aimcup.tournament.data.entity.tournament.TeamBasedTournament;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "seed")
    private Integer seed;

    @Column(name = "average_performance_points", nullable = false)
    private BigDecimal averagePerformancePoints;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "captain", referencedColumnName = "id")
    private Participant captain;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tournament_teams",
        joinColumns = @JoinColumn(name = "tournament_id"),
        inverseJoinColumns = @JoinColumn(name = "team_id"))
    private TeamBasedTournament tournament;

    @OneToMany(mappedBy = "team")
    private Set<Participant> participants;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "teams")
    private Set<TeamBasedMatch> matches;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "qualification_room_id", referencedColumnName = "id")
    private TeamBasedQualificationRoom qualificationRoom;

}
