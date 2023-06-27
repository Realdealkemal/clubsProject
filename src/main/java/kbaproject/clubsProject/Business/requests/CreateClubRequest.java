package kbaproject.clubsProject.Business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClubRequest{

        @NotNull
        @NotBlank
        private String name;

        private int trophiesCount;

        private String presidentName;

        private String presidentEmail;
}
