package kbaproject.clubsProject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name="President")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class President {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name="Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @JsonIgnore
    @OneToOne
    private Club club;

    public President(String name,String email){
        this.name=name;
        this.email=email;
    }
}
