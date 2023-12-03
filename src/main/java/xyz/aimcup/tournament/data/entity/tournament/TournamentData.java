package xyz.aimcup.tournament.data.entity.tournament;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TournamentData {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID tournamentId;

    @OneToOne(mappedBy = "tournamentData")
    private Tournament tournament;

    @Column(nullable = false)
    @Max(8)
    @Min(1)
    private Integer teamSize;

    @Column(nullable = false)
    private Integer maxTeamSize;

    @Column(nullable = false)
    private Integer minimumRankLimit;

    @Column(nullable = false)
    private Integer maximumRankLimit;

    @Column(nullable = false)
    @Max(16)
    @Min(1)
    private Integer participantsPerQualificationSpotLimit;

    public Integer calculateNumberOfQualificationSpots() {
        return participantsLimit / participantsPerQualificationSpotLimit + 1;
    }
}
