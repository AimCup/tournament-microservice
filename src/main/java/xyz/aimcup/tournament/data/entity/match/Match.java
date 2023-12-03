package xyz.aimcup.tournament.data.entity.match;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import xyz.aimcup.tournament.data.entity.participant.Participant;
import xyz.aimcup.tournament.data.entity.phase.BracketsPhase;
import xyz.aimcup.tournament.data.entity.qualification.QualificationGroup;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@DiscriminatorColumn(name = "match_type")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "match_type", nullable = false, insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private MatchType matchType;

    @Column(name = "tournament_id", nullable = false, updatable = false)
    private UUID tournamentId;

    @Column(name = "brackets_phase_id", nullable = false, insertable = false, updatable = false)
    private UUID bracketsPhaseId;

    @Column(name = "qualification_group_id", nullable = false, insertable = false, updatable = false)
    private UUID qualificationGroupId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "match_participants",
        joinColumns = @JoinColumn(name = "match_id"),
        inverseJoinColumns = @JoinColumn(name = "participant_id"))
    private Set<Participant> participants;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "brackets_phase_id", referencedColumnName = "id")
    private BracketsPhase bracketsPhase;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "qualification_group_id", referencedColumnName = "id")
    private QualificationGroup qualificationGroup;

}

