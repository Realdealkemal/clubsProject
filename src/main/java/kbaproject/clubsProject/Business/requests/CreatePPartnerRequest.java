package kbaproject.clubsProject.Business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePPartnerRequest {
    private String name;
    private String contactNumber;
    private int playerId;
}
