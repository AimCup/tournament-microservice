package xyz.aimcup.tournament.feign.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.aimcup.tournament.feign.user.models.UserResponseDTO;

@FeignClient("user-microservice")
public interface UserApiClient {

    @GetMapping("/user/{osuId}")
    ResponseEntity<UserResponseDTO> getUserByOsuId(@PathVariable String osuId);
}
