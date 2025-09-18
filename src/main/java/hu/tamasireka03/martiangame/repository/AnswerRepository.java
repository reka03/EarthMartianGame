package hu.tamasireka03.martiangame.repository;

import hu.tamasireka03.martiangame.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("SELECT a FROM Answer a WHERE a.part.partId = :partId")
    List<Answer> findByPartId(@Param("partId") Long partId);
}