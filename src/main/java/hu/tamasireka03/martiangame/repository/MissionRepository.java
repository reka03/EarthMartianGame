package hu.tamasireka03.martiangame.repository;

import hu.tamasireka03.martiangame.entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
