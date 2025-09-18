package hu.tamasireka03.martiangame.repository;

import hu.tamasireka03.martiangame.entities.MissionPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionPartRepository extends JpaRepository<MissionPart, Long> {
    @Query("SELECT mp FROM MissionPart mp WHERE mp.mission.missionId = :missionId")
    List<MissionPart> findByMissionId(@Param("missionId") Long missionId);
}
