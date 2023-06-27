package kbaproject.clubsProject.Business.concretes;


import kbaproject.clubsProject.Business.abstracts.PlayerService;
import kbaproject.clubsProject.Business.abstracts.TrainerService;
import kbaproject.clubsProject.Business.requests.CreateTrainerRequest;
import kbaproject.clubsProject.Business.responses.GetAllPlayersResponse;
import kbaproject.clubsProject.Business.responses.GetAllTrainersResponse;
import kbaproject.clubsProject.DataAccess.abstracts.ClubRepository;
import kbaproject.clubsProject.DataAccess.abstracts.TrainerRepository;
import kbaproject.clubsProject.core.utilities.mappers.ModelMapperService;
import kbaproject.clubsProject.entities.PersonalTrainer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor

public class TrainerServiceImpl implements TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ModelMapperService modelMapperService;
    @Override
    public List<GetAllTrainersResponse> getAll() {
        List<PersonalTrainer> trainers= this.trainerRepository.findAll();

        List<GetAllTrainersResponse> trainersResponse=trainers.stream()
                                                .map(trainer -> this.modelMapperService.forResponse()
                                                        .map(trainer, GetAllTrainersResponse.class)).collect(Collectors.toList());

        return trainersResponse;

    }

    @Override
    public void addT(CreateTrainerRequest createTrainerRequest) {
        PersonalTrainer personalTrainer =this.modelMapperService.forRequest().map(createTrainerRequest, PersonalTrainer.class);
        this.trainerRepository.save(personalTrainer);

    }

    @Override
    public List<GetAllTrainersResponse> getByClub_name(String clubName) {

        List<GetAllPlayersResponse> playersResponses = this.playerService.findByClubName(clubName);
        List<Integer> playerIdList = playersResponses.stream().map(presponses-> presponses.getPlayerId()).collect(Collectors.toList());
        //her bir playersin player ıdsını integer listesi olacak şekiilde dönğştürmeye yarar

        List<PersonalTrainer> trainers=this.trainerRepository.getByPlayers_idIn(playerIdList);



        List<GetAllTrainersResponse> trainersResponses= trainers.stream()
                                                          .map(trainer ->this.modelMapperService.forResponse()
                .map(trainer,GetAllTrainersResponse.class)).collect(Collectors.toList());

        return trainersResponses;
    }
}
