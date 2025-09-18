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
@Table(name = "MissionPart")
public class MissionPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partId;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;

    private int partOrder;
    private String type;
    private String question;
    private String alienText;
    private int rewardChocolate;

    @OneToMany(mappedBy = "part")
    private List<Answer> answers;
}
