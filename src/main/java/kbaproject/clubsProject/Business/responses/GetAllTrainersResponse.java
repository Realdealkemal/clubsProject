package kbaproject.clubsProject.Business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTrainersResponse {
    private int id;

    private String name;
}
