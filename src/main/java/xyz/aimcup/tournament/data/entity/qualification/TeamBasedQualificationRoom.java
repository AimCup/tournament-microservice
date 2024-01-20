package xyz.aimcup.tournament.data.entity.qualification;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import xyz.aimcup.tournament.data.entity.qualification.QualificationRoomType.QualificationRoomTypeNames;
import xyz.aimcup.tournament.data.entity.team.Team;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@DiscriminatorValue(QualificationRoomTypeNames.TEAM_VS)
public class TeamBasedQualificationRoom extends QualificationRoom {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qualificationRoom")
    private Set<Team> teams;
}
