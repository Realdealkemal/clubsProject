package kbaproject.clubsProject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "PlayersPartner")
public class PlayersPartner {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name="Name")
    private String name;

    @Column(name = "ContactNumber")
    private String contactNumber;

    @JsonIgnore
    @OneToOne

    private Player player;

    public PlayersPartner(String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
    }
    @Override
    public String toString(){
        return "id: "+id+"name: "+name+" contactNumber: "+contactNumber;

    }
}
