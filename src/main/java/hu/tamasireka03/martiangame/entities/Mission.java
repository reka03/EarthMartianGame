package hu.tamasireka03.martiangame.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Mission")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionId;

    private String title;
    private String description;

    @OneToMany(mappedBy = "mission")
    private List<MissionPart> parts;
}