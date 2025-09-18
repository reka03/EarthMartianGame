package hu.tamasireka03.martiangame.repository;

import hu.tamasireka03.martiangame.entities.DiaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DiaryEntryRepository extends JpaRepository<DiaryEntry, Long> {
    @Query("SELECT d FROM DiaryEntry d WHERE d.user.userid = :userId")
    List<DiaryEntry> findByUserId(@Param("userId") Long userId);

    @Query("SELECT d FROM DiaryEntry d WHERE d.mission.missionId = :missionId")
    List<DiaryEntry> findByMissionId(@Param("missionId") Long missionId);

    @Query("SELECT d FROM DiaryEntry d WHERE d.user.userid = :userId AND d.mission.missionId = :missionId")
    Optional<DiaryEntry> findByUserIdAndMissionId(@Param("userId") Long userId, @Param("missionId") Long missionId);
}