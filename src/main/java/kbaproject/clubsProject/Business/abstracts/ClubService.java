package kbaproject.clubsProject.Business.abstracts;

import kbaproject.clubsProject.Business.responses.GetAllClubsResponse;
import kbaproject.clubsProject.Business.responses.GetClubById;
import kbaproject.clubsProject.Business.requests.CreateClubRequest;
import kbaproject.clubsProject.Business.requests.UpdateClubRequest;
import kbaproject.clubsProject.entities.Club;
import kbaproject.clubsProject.entities.PlayersPartner;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface ClubService {
    List<GetAllClubsResponse> getAll();
    GetClubById getById(int id);
    void add(CreateClubRequest createClubRequest);
    void delete(int id);

    void update(UpdateClubRequest updateClubRequest);

    List<GetAllClubsResponse> getByTrainerName(String trainerName);
    List<GetAllClubsResponse> getByTrophiesCountBetween(int a,int b);
    GetAllClubsResponse getByPlayerId(int id);
    GetClubById getByPlayerIdWithEntıtyManager(int id);
    Long playersCounting(int id);



}
