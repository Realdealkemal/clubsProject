package kbaproject.clubsProject.DataAccess.abstracts;

import kbaproject.clubsProject.Business.requests.CreatePPartnerRequest;
import kbaproject.clubsProject.entities.Player;
import kbaproject.clubsProject.entities.PlayersPartner;

import java.util.List;
import java.util.Optional;

public interface PlayersPartnerRepository {
    void save(PlayersPartner playersPartner);
    PlayersPartner findById(int id);

    void delete(int id);

    List<PlayersPartner> getAll();

    List<PlayersPartner> getByName(String name);

    PlayersPartner getByPlayerId(int id);

    List<PlayersPartner> getByClubsName(String name);

    void update(PlayersPartner playersPartner,int id);

    List<PlayersPartner> checkIfExistsAnyPlayersPartner(String contactNumber, String name);

    List<PlayersPartner> listsTop3PlayersPartner();

    List<PlayersPartner> getAvgNameJenniferandCeyse(String name,String name1);

    List<PlayersPartner> getByTeams(String teamName);

    List<PlayersPartner> pageablePartners(int pageNo, int pageSize);
    List<PlayersPartner> contactNumberCharacterGreater(int a);

}
