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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import xyz.aimcup.tournament.data.entity.qualification.QualificationGroup;
import xyz.aimcup.tournament.data.entity.qualification.QualificationRoom;
import xyz.aimcup.tournament.data.entity.qualification.QualificationType;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "tournament_type", nullable = false, insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private TournamentType tournamentType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QualificationType qualificationType;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    @MapsId
    private TournamentConstraints tournamentConstraints;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournament", orphanRemoval = true)
    private Set<QualificationGroup> qualificationGroups;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournament", orphanRemoval = true)
    private Set<QualificationRoom> qualificationRooms;

    @Column(nullable = false)
    @Builder.Default
    private Boolean signingEnabled = false;

    @Column(nullable = false)
    private UUID createdBy;

    public Integer calculateNumberOfQualificationSpots() {
        return tournamentConstraints.getParticipantsLimit()
            / tournamentConstraints.getParticipantsPerQualificationSpotLimit();
        //TODO: EXTEND THIS TO THROW ERROW
    }
}
