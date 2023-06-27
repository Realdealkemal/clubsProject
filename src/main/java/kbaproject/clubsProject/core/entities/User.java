package kbaproject.clubsProject.core.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User { //kullanıcı girişleri her projede kullanıldığı için core katmanında tutmak istiyorum
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;


    @Column(name = "name")
    private  String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private  String password;


    @Column(name = "roles")
    private String roles;



}
