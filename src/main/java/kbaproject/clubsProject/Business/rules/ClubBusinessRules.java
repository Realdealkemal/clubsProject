package kbaproject.clubsProject.Business.rules;

import kbaproject.clubsProject.Business.concretes.PlayersPartnerImpl;
import kbaproject.clubsProject.DataAccess.abstracts.ClubRepository;
import kbaproject.clubsProject.DataAccess.abstracts.PlayerRepository;
import kbaproject.clubsProject.core.utilities.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClubBusinessRules {
    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlayersPartnerImpl playersPartnerImpl;
    public void checkIfClubNameExists(String name){
        if(this.clubRepository.existsByName(name)){
            throw new BusinessException("Club already exists");
        }
    }
    public void checkIfExistsByPlayerName(String name){
        if(this.playerRepository.existsByName(name)){
            throw new BusinessException("Player already exists");
        }
    }
    public void checkIfExistsAnyClub(){
        if(this.clubRepository.count()==0){
            throw  new BusinessException("there areny any club");
        }
    }

    public void checkIfExistsAnyPlayersPartner(String contactNumber,String name){
        if(playersPartnerImpl.checkIfExistsAnyPlayersPartner(contactNumber,name).isEmpty()){
            throw new BusinessException("There arent any in players partner that name and contact name");

        }

    }

}
