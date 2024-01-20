package xyz.aimcup.tournament.data.entity.tournament;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import xyz.aimcup.tournament.data.entity.team.Team;
import xyz.aimcup.tournament.data.entity.tournament.TournamentType.TournamentTypeNames;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@DiscriminatorValue(TournamentTypeNames.TEAM_VS)
public class TeamBasedTournament extends Tournament {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournament")
    private Set<Team> teams;

    @Override
    public Integer calculateNumberOfQualificationSpots() {
        return teams.size() / getTournamentData().getParticipantsPerQualificationSpotLimit() + 1;
    }
}
