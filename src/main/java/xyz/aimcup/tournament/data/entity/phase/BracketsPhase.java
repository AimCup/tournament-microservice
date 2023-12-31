package xyz.aimcup.tournament.data.entity.phase;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import xyz.aimcup.tournament.data.entity.match.Match;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class BracketsPhase extends TournamentPhase {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    private Tournament tournament;

    @Column(name = "seed_size", nullable = false)
    private Integer seedSize;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bracketsPhase", orphanRemoval = true)
    private Set<Match> matches;
}
