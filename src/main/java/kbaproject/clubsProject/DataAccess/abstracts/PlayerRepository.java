package kbaproject.clubsProject.DataAccess.abstracts;

import jakarta.persistence.Tuple;
import kbaproject.clubsProject.Business.responses.GetAllPlayersResponse;
import kbaproject.clubsProject.entities.Club;
import kbaproject.clubsProject.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface
PlayerRepository extends JpaRepository<Player,Integer> {


    List<Player> findByClubName(String name);
    Player getByPlayersPartnerName(String name);
    List<Player> getByTrainerNameAndClubName(String trainerName,String clubname); //getByTrainerNameAndClubName metodu işe aynı iş yapar


  @Query(value="select p.player_id,p.player_name,p.club_id,p.trainer_id from players p,personal_trainers pt,clubs c where p.club_id =c.club_id and p.trainer_id= pt.trainer_id and pt.personal_name=?1 and c.club_name=?2",nativeQuery = true)
   List<Player> getTNameAndClub(String personalTrainerName, String clubName);


   /* @Query("select new kbaproject.clubsProject.entities.Player( p.id,p.name,p.club,p.trainer) " +
            "From Player pl"+
            "inner join PersonalTrainer pt on pl.trainer= pt.id"+
            "inner join Club cb on pl.club=cb.id+" +
            "and pt.name=:personalTrainerName, and c.name=:clubName")
    List<Player> getTNameAndClub(String personalTrainerName, String clubName);*/

    boolean existsByName(String name);
    List<Player> getByTrainerName(String name);

    List<Player> getByIdGreaterThanOrderById(int comparableNum);
    List<Player> getByClubIdIn(List<Integer> playersIdList) ;




}
