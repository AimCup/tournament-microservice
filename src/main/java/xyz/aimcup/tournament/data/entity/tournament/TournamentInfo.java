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
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Builder
public class TournamentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID tournamentId;

    @OneToOne(mappedBy = "tournamentInfo")
    private Tournament tournament;

    @Column(name = "rules", length = Length.LOB_DEFAULT)
    private String rules;

    @Column(name = "prizes", length = Length.LOB_DEFAULT)
    private String prizes;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "updated_at")
    private LocalDateTime createdAt;

    @Column(name = "version")
    private Integer version;

}
