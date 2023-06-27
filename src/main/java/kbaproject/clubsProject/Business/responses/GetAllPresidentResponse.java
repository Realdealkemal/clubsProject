package kbaproject.clubsProject.Business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPresidentResponse {
    private int id;

    private String name;

    private String email;

    private String clubName;
}
