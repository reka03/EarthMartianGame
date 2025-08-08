package hu.tamasireka03.martiangame.repository;

import hu.tamasireka03.martiangame.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String Username);
    Optional<User> findByMartianName(String martianName);
}
