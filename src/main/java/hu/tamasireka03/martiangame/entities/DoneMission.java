package hu.tamasireka03.martiangame.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "DoneMission")
public class DoneMission {
    @EmbeddedId
    private DoneMissionId id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("missionId")
    private Mission mission;

    private LocalDateTime complatedAt = LocalDateTime.now();
}