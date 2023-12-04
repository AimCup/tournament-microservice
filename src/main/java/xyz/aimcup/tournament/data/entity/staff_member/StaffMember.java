package xyz.aimcup.tournament.data.entity.staff_member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.aimcup.tournament.data.entity.tournament.Tournament;
import xyz.aimcup.tournament.data.entity.tournament_role.Permission;
import xyz.aimcup.tournament.data.entity.tournament_role.TournamentRole;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

}
