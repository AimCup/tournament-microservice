package xyz.aimcup.tournament.data.entity.match;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import xyz.aimcup.tournament.data.entity.match.MatchType.MatchTypeNames;
import xyz.aimcup.tournament.data.entity.team.Team;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@DiscriminatorValue(MatchTypeNames.TEAM_VS)
public class TeamBasedMatch extends Match {

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "match_teams",
        joinColumns = @JoinColumn(name = "match_id"),
        inverseJoinColumns = @JoinColumn(name = "team_id"))
    private Set<Team> teams;
}