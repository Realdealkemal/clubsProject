package kbaproject.clubsProject.Service;

import jakarta.inject.Inject;
import kbaproject.clubsProject.Business.abstracts.PlayerService;
import kbaproject.clubsProject.Business.concretes.ClubServiceImpl;
import kbaproject.clubsProject.Business.responses.GetClubById;
import kbaproject.clubsProject.Business.rules.ClubBusinessRules;
import kbaproject.clubsProject.DataAccess.abstracts.ClubRepository;
import kbaproject.clubsProject.core.utilities.mappers.ModelMapperService;
import kbaproject.clubsProject.entities.Club;
import kbaproject.clubsProject.entities.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClubServiceTest {
    @InjectMocks  //test edeceğimiz classı inject ediyoruz
    private ClubServiceImpl sut; //test edeceğimiz classtır

    @Mock //t4est edeceğimiz class içindeki bölümleri mock olarak çağırmamız gerek. Aksi halde gerçek bir veritabanına bağlanmamız gerekecek
    private ClubRepository clubRepository;

    @Mock
    private ModelMapperService modelMapperService;

    @Mock
    private PlayerService playerService;

    @Mock
    private ClubBusinessRules clubBusinessRules;


    @BeforeEach //testleri sıfırlamak için kullanırız
    public void setup(){


        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Testin clubById kısmı çaılışıyor")
    public void whenGetByIdCalledWithRequets_ReturnValidAccountDto(){ //bir test yeni bir parçayı test eder. bir metod içinde yeni bir şey olursa yeni Testler yazmalıyız
        int id =1;

        Club result = new Club(); //rastgele bir club objesi oluşturur ve içine rastgele değerler koyarız
        result.setName("someName");
        result.setPlayers(new ArrayList<Player>());
        result.setTrophiesCount(231);


        //findByıdi çalıştırıldığında result objemiz geri verilir


        GetClubById expectedResponse = new GetClubById();

        expectedResponse.setName("someName");
        expectedResponse.setTrophiesCount(231);



        Mockito.when(clubRepository.findById(id)).thenReturn(Optional.of(result));
        Mockito.when(modelMapperService.forResponse().map(result, GetClubById.class)).thenReturn(expectedResponse);

        GetClubById result1= sut.getById(id);

        assertEquals(expectedResponse, result1);

        Mockito.verify(sut).getById(id);
        Mockito.verify(modelMapperService).forResponse().map(result, Club.class);


    }

   /* @Test
    @DisplayName("Testin mapper kısmı çalıştı")
    public void shouldgetb(){
        int id =1;

        Club result = new Club(); //rastgele bir club objesi oluşturur ve içine rastgele değerler koyarız

        result.setName("someName");
     //   result.setPlayers(new ArrayList<Player>());
        result.setTrophiesCount(231);

        GetClubById expectedResponse = new GetClubById();

        expectedResponse.setName("someName");
        expectedResponse.setTrophiesCount(321);

        sut.getById(id);


        verify(clubRepository).findById(id);


    }*/
}
