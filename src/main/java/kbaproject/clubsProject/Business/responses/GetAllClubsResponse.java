package kbaproject.clubsProject.Business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllClubsResponse {

    private int id;

    private String name;

    private int trophiesCount;
    private String presidentName;

}
