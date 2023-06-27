package kbaproject.clubsProject.Business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPPartnerResponse {
    private int id;
    private String name;
    private String contactNumber;
    private int playerId;
}
