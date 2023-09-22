package xyz.aimcup.tournament.data.entity.qualification;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import xyz.aimcup.tournament.data.entity.phase.QualificationPhase;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class QualificationGroup implements QualificationSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Max(16)
    private Integer participantsLimit;


    @Column(name = "qualification_phase_id", nullable = false, insertable = false, updatable = false)
    private UUID qualificationPhaseId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qualification_phase_id", referencedColumnName = "id")
    private QualificationPhase qualificationPhase;


}
