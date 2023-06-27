package kbaproject.clubsProject.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "Players")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "playerId",updatable = false)
    private int id;

    @Column(name = "playerName")
    private String name;

    @ManyToOne
    @JoinColumn(name = "clubId") //
    private Club club;

    @ManyToOne
    @JoinColumn(name="trainerId")
    private PersonalTrainer trainer;


    @OneToOne(cascade =CascadeType.ALL) // Partner Idnin ilişkisi kesildiği an kaldırılır
    @JoinColumn(name = "partnerId")
    private PlayersPartner playersPartner;


}
