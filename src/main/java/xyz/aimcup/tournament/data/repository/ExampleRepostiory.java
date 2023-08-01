package xyz.aimcup.tournament.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.aimcup.tournament.data.entity.Example;

import java.util.UUID;

@Repository
public interface ExampleRepostiory extends JpaRepository<Example, UUID> {
}
