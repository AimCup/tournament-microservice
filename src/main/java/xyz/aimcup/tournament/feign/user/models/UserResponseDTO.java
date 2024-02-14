package xyz.aimcup.tournament.feign.user.models;

import lombok.Getter;

import java.util.UUID;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private UUID id;
}
