package kbaproject.clubsProject.Business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePlayerRequest {
    private int id;
    private String name;
    private int clubId;
    private int trainerId;
    private String partnerName;
    private String contactNumber;
}
