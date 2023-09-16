package xyz.aimcup.tournament.data.entity.tournament;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
public class TournamentConstraints {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID tournamentId;

    @OneToOne(mappedBy = "tournamentConstraints")
    private Tournament tournament;

    @Column(nullable = false)
    private Integer participantsLimit;

    @Column(nullable = false)
    private Integer refereesLimit;

    @Column(nullable = false)
    private Integer commentatorsLimit;

    @Column(nullable = false)
    private Integer streamersLimit;

    @Column(nullable = false)
    private Integer playersPerBeatmapLimit;

    private Integer minimumRankLimit;

    private Integer maximumRankLimit;

    @Column(nullable = false)
    private Integer participantsPerQualificationSpotLimit;

    //todo: ADD VALIDATION TO MAX, MIN RANK, AND TO
}
