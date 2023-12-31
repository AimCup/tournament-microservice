package xyz.aimcup.tournament.data.entity.tournament;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import xyz.aimcup.tournament.data.entity.phase.BracketsPhase;
import xyz.aimcup.tournament.data.entity.phase.QualificationPhase;
import xyz.aimcup.tournament.data.entity.phase.RegistrationPhase;
import xyz.aimcup.tournament.data.entity.qualification.QualificationType;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@SuperBuilder
@DiscriminatorColumn(name = "tournament_type")
public abstract class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String abbreviation;

    @Column(nullable = false)
    private UUID createdBy;


    @Column(name = "tournament_type", nullable = false, insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private TournamentType tournamentType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QualificationType qualificationType;


    @OneToOne(mappedBy = "tournament", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private TournamentData tournamentData;

    @OneToOne(mappedBy = "tournament", cascade = CascadeType.ALL)
    private TournamentInfo tournamentInfo;

    @OneToOne(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private RegistrationPhase registrationPhase;

    @OneToOne(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private QualificationPhase qualificationPhase;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournament", orphanRemoval = true)
    private Set<BracketsPhase> bracketsPhases;


    public abstract Integer calculateNumberOfQualificationSpots();
}
