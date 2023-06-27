package kbaproject.clubsProject.Business.responses;

import jakarta.persistence.*;
import kbaproject.clubsProject.entities.Club;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPlayersResponse {

    private int playerId;

    private String name;

    private String clubName;

    private String trainerName;
    private String partnerName;

}
