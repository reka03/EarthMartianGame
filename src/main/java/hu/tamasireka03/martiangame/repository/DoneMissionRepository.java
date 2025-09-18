package hu.tamasireka03.martiangame.repository;

import hu.tamasireka03.martiangame.entities.DoneMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoneMissionRepository extends JpaRepository<DoneMission, Long> {
    @Query("SELECT CASE WHEN COUNT(dm) > 0 THEN true ELSE false END FROM DoneMission dm " +
            "WHERE dm.user.userid = :userId AND dm.mission.missionId = :missionId")
    boolean existsByUserIdAndMissionId(@Param("userId") Long userId, @Param("missionId") Long missionId);

    @Query("SELECT dm FROM DoneMission dm WHERE dm.user.userid = :userId")
    List<DoneMission> findByUserId(@Param("userId") Long userId);
}