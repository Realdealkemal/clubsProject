package kbaproject.clubsProject.DataAccess.abstracts;

import kbaproject.clubsProject.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club,Integer> {
    boolean existsByName(String name);
    //boolean existsByTrophiesCount(int trophiesCount);
    long count();
    List<Club> getByPlayers_idIn(List<Integer> playersIdList) ;
    List<Club> getByTrophiesCountBetween(int a,int b);
   Club findByPlayersId(int id);
   // Club getByPlayerIdWithEntıtyManager(int id);

}
