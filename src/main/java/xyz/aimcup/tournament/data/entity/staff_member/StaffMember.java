package xyz.aimcup.tournament.data.entity.staff_member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.aimcup.tournament.data.entity.match.Match;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament_role.Permission;
import xyz.aimcup.tournament.data.entity.tournament_role.TournamentRole;

@Entity
@Table(name = "staff_members")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffMember {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "joined_at")
    private LocalDateTime joinedAt;

    @Column(name = "discord_id")
    private String discordId;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToMany
    @JoinTable(
            name = "staff_member_roles",
            joinColumns = @JoinColumn(name = "staff_member_id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_role_id")
    )
    private List<TournamentRole> roles;

    @Enumerated(EnumType.STRING)
    private List<Permission> permissions;

    @Column(name = "user_id")
    private UUID userId;

    @OneToMany(mappedBy = "referee", fetch = FetchType.LAZY)
    private Set<Match> refereedMatches;

    @OneToMany(mappedBy = "streamer", fetch = FetchType.LAZY)
    private Set<Match> streamedMatches;

    @ManyToMany(mappedBy = "commentators", fetch = FetchType.LAZY)
    private Set<Match> castedMatches;

}
