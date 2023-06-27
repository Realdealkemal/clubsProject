package kbaproject.clubsProject.Service;

import jakarta.persistence.EntityManager;
import kbaproject.clubsProject.Business.concretes.PlayersPartnerImpl;
import kbaproject.clubsProject.Business.responses.GetClubById;
import kbaproject.clubsProject.DataAccess.abstracts.PlayersPartnerRepository;
import kbaproject.clubsProject.entities.Club;
import kbaproject.clubsProject.entities.Player;
import kbaproject.clubsProject.entities.PlayersPartner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayersPartnerService {

    @InjectMocks
    private PlayersPartnerImpl sut;

    @Mock
    private EntityManager entityManager;

    @Mock
    private PlayersPartnerRepository repository;

    @BeforeEach //testleri sıfırlamak için kullanırız
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testin clubById kısmı çaılışıyor")
    void should_get_by_club_id(){ //bir test yeni bir parçayı test eder. bir metod içinde yeni bir şey olursa yeni Testler yazmalıyız
        int id =1;

        PlayersPartner result = new PlayersPartner(); //rastgele bir club objesi oluşturur ve içine rastgele değerler koyarız

        result.setPlayer(new Player());
        result.setName("name");
        result.setContactNumber("number");

        when(repository.findById(id)).thenReturn(result);
        //findByıdi çalıştırıldığında result objemiz geri verilir
        sut.getByPlayerId(id) ;//test ettiğimiz classdaki methodu çalıştırırız

        verify(repository).findById(id) ;//son olarak doğrularuz
    }


}
