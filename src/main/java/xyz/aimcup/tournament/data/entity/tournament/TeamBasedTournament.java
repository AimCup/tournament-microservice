package xyz.aimcup.tournament.data.entity.tournament;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import xyz.aimcup.tournament.data.entity.tournament.TournamentType.TournamentTypeNames;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@DiscriminatorValue(TournamentTypeNames.TEAM_VS)
public class TeamBasedTournament extends Tournament {

}
