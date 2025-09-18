package hu.tamasireka03.martiangame.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String martianName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int chocolateCount;

    @OneToMany(mappedBy = "user")
    private List<DiaryEntry> diaryEntries;

    @OneToMany(mappedBy = "user")
    private List<DoneMission> doneMissions;
}