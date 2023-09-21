package xyz.aimcup.tournament.data.entity.tournament;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Length;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TournamentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(mappedBy = "tournamentInfo")
    private TournamentData tournamentData;

    @Column(name = "rules", length = Length.LOB_DEFAULT)
    private String rules;

    @Column(name = "prizes", length = Length.LOB_DEFAULT)
    private String prizes;

    @Column(name = "edited_by", nullable = false)
    private UUID editedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
