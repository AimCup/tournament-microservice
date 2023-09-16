package xyz.aimcup.tournament.data.entity.qualification;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class QualificationGroup implements QualificationSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    private Tournament tournament;

    @Column(nullable = false)
    private Integer participantsLimit;

}
