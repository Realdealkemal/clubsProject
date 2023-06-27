package kbaproject.clubsProject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "PersonalTrainers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PersonalTrainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TrainerId",updatable = false)
    private int id;
    @Column(name = "PersonalName")
    private String name;

    @OneToMany(mappedBy = "trainer")
    private List<Player> players;
}