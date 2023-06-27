package kbaproject.clubsProject.Business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    private int playerId;
    private int fromClubId;
    private int toClubId;

}
