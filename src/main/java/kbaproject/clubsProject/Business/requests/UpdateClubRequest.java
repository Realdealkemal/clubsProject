package kbaproject.clubsProject.Business.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClubRequest { //kullanıcıdan update alırkan bu 3 parametreyi de iste demek


    private int id;
    private String name;

    private int trophiesCount;
    private String presidentName;

    private String presidentEmail;

}
