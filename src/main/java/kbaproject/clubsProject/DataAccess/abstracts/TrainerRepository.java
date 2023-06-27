package kbaproject.clubsProject.DataAccess.abstracts;

import kbaproject.clubsProject.entities.PersonalTrainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerRepository extends JpaRepository<PersonalTrainer,Integer> {
    List<PersonalTrainer> getByPlayers_idIn(List<Integer> playerIdList);
    //her ne kadar column açmasak da PersonalTrainer entitysi içerisinde playerları tutarız
    //bu sebeple players idsine göre bu şekilde personal trainerlara ulaşırız

}
