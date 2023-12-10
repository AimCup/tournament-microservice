package xyz.aimcup.tournament.data.entity.phase;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import xyz.aimcup.tournament.data.entity.qualification.QualificationGroup;
import xyz.aimcup.tournament.data.entity.qualification.QualificationRoom;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class QualificationPhase extends TournamentPhase {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qualificationPhase", orphanRemoval = true)
    private Set<QualificationGroup> qualificationGroups;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qualificationPhase", orphanRemoval = true)
    private Set<QualificationRoom> qualificationRooms;

    @OneToOne
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    private Tournament tournament;


}
