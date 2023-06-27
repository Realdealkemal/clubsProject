package kbaproject.clubsProject.Business.abstracts;

import kbaproject.clubsProject.Business.requests.CreateClubRequest;
import kbaproject.clubsProject.Business.requests.CreatePlayerRequest;
import kbaproject.clubsProject.Business.requests.UpdateClubRequest;
import kbaproject.clubsProject.Business.requests.UpdatePlayerRequest;
import kbaproject.clubsProject.Business.responses.GetAllPlayersResponse;
import kbaproject.clubsProject.entities.Player;

import java.util.List;

public interface PlayerService {
    List<GetAllPlayersResponse> getAll();
    void adds(CreatePlayerRequest createPlayerRequest);
    List<GetAllPlayersResponse> findByClubName(String name);
    List<GetAllPlayersResponse> getByTrainerNameAndClubName(String trainerName,String clubname);
    List<GetAllPlayersResponse> getByTrainerName(String name);
    void delete(int id);

    GetAllPlayersResponse getByPlayersPartnerName(String name);

    //
     List<GetAllPlayersResponse> getTNameAndClub(String trainerName,String clubName);



    List<GetAllPlayersResponse> getAllP(int pageNo,int pageSize);
    List<GetAllPlayersResponse> getAllSorted();

    void update(UpdatePlayerRequest updatePlayerRequest);

    List<GetAllPlayersResponse> getByIdGraterThanOrderById(int comparableNum);
}
