package kbaproject.clubsProject.webApi.Controller;

import kbaproject.clubsProject.Business.abstracts.TrainerService;
import kbaproject.clubsProject.Business.requests.CreateTrainerRequest;
import kbaproject.clubsProject.Business.responses.GetAllTrainersResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api"})
@NoArgsConstructor
@AllArgsConstructor
public class TrainerController {
    @Autowired
    private TrainerService trainerService;

    @PostMapping("/add/trainers")
    public void addt(@RequestBody CreateTrainerRequest createTrainerRequest){

        this.trainerService.addT(createTrainerRequest);
    }
    @GetMapping("/trainers/getall")
    public List<GetAllTrainersResponse> getAllT(){

        return this.trainerService.getAll();
    }
    @GetMapping("/trainers/getplnameclubname/{clubName}")
    public List<GetAllTrainersResponse> getByClub_name(@PathVariable String clubName) {

        return this.trainerService.getByClub_name(clubName);
    }





}
