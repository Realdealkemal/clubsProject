package kbaproject.clubsProject.Business.abstracts;

import kbaproject.clubsProject.Business.requests.CreateTrainerRequest;
import kbaproject.clubsProject.Business.responses.GetAllTrainersResponse;
import kbaproject.clubsProject.entities.PersonalTrainer;

import java.util.List;

public interface TrainerService {
    List<GetAllTrainersResponse> getAll();
    void addT(CreateTrainerRequest createTrainerRequest);
    List<GetAllTrainersResponse> getByClub_name(String clubName);
}
