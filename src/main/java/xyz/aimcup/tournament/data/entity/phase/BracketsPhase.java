package xyz.aimcup.tournament.data.entity.phase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class BracketsPhase implements TournamentPhase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer seedSize;


    @Column(name = "tournament_id", nullable = false, insertable = false, updatable = false)
    private UUID tournamentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    private Tournament tournament;
}
