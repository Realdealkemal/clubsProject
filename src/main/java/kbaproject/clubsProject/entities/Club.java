package kbaproject.clubsProject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Table(name = "Clubs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClubId",updatable = false)
    private int id;

    @Column(name = "ClubName")
    private String name;

    @Column(name = "TrophiesCount")
    private int trophiesCount;

    @OneToMany(mappedBy = "club")
    private List<Player> players;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "presidentId")
    private President president;
}
