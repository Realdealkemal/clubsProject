package kbaproject.clubsProject.Business.concretes;

import jakarta.persistence.EntityManager;
import kbaproject.clubsProject.Business.abstracts.PlayerService;
import kbaproject.clubsProject.Business.requests.*;
import kbaproject.clubsProject.Business.responses.GetAllClubsResponse;
import kbaproject.clubsProject.Business.responses.GetAllPlayersResponse;
import kbaproject.clubsProject.Business.responses.GetClubById;
import kbaproject.clubsProject.Business.rules.ClubBusinessRules;
import kbaproject.clubsProject.DataAccess.abstracts.PlayerRepository;
import kbaproject.clubsProject.core.utilities.exceptions.BusinessException;
import kbaproject.clubsProject.core.utilities.mappers.ModelMapperService;
import kbaproject.clubsProject.entities.Club;
import kbaproject.clubsProject.entities.Player;
import kbaproject.clubsProject.entities.PlayersPartner;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private ModelMapperService modelMapperService;
    @Autowired
    private ClubBusinessRules clubBusinessRules;
    @Autowired
    private PlayersPartnerImpl playersPartnerImpl;
    public GetAllPlayersResponse getById(int id) {
        Player player=this.playerRepository.findById(id).orElseThrow(()-> new BusinessException("Bu idde takım bulunamadı"));

        GetAllPlayersResponse response= this.modelMapperService.forResponse().map(player,GetAllPlayersResponse.class);


        return response;

    }

    @Override
    public List<GetAllPlayersResponse> getAll() {
        List<Player> players=playerRepository.findAll(); //getAllClubsResponses tipinde döndürmem lazıma ama findAll metodu ile nesnelerimin hepsini bir dizide tut

        List<GetAllPlayersResponse> playerResponse=players.stream() //her bir clubs için
                .map(player -> this.modelMapperService.forResponse()
                        .map(player,GetAllPlayersResponse.class)).collect(Collectors.toList()); //ilk map stream api'ın mapidir.eldeki listeyi tek tek dolaşmayı sağlar
        //her bir brand için modelservicein mapini kullaanrak mapleme yap
        //tolist metoduyla listeye çevirir
        return playerResponse ;
    }

    @Override
    public void adds(CreatePlayerRequest createPlayerRequest) {
        this.clubBusinessRules.checkIfExistsByPlayerName(createPlayerRequest.getName());
        Player player=this.modelMapperService.forRequest().map(createPlayerRequest,Player.class);

        if(createPlayerRequest.getPartnerName()!=null){ //eğer playerspartnerı varsa yeni bir partner oluşturuyor. yoksa direkt bu alanı boş geçiyoruz
            PlayersPartner playersPartner = new PlayersPartner(createPlayerRequest.getPartnerName(),createPlayerRequest.getContactNumber());
            player.setPlayersPartner(playersPartner); // ana nesne kaydedilmeden önce ilişkilendirilir
            playersPartner.setPlayer(player); //ilikili nesne ilişkilendirilir
       // Ana nesne kaydedilir cascede All verdiğimiz için diğer nesne de kaydedilmiş olur

        }
        this.playerRepository.save(player);



    }


    @Override
    public List<GetAllPlayersResponse> findByClubName(String name) {
        List<Player> players=this.playerRepository.findByClubName(name); //getAllClubsResponses tipinde döndürmem lazıma ama findAll metodu ile nesnelerimin hepsini bir dizide tut

        List<GetAllPlayersResponse> playerResponse=players.stream() //her bir clubs için
                .map(player -> this.modelMapperService.forResponse()
                        .map(player,GetAllPlayersResponse.class)).collect(Collectors.toList()); //ilk map stream api'ın mapidir.eldeki listeyi tek tek dolaşmayı sağlar
        //her bir brand için modelservicein mapini kullaanrak mapleme yap
        //tolist metoduyla listeye çevirir
        return playerResponse ;
    }


    @Override
    public List<GetAllPlayersResponse> getByTrainerNameAndClubName(String trainerName, String clubname) {
      List<Player> pByTname=this.playerRepository.getByTrainerNameAndClubName(trainerName,clubname);

        List<GetAllPlayersResponse> playerResponse=pByTname.stream() //
                .map(player -> this.modelMapperService.forResponse()
                        .map(player,GetAllPlayersResponse.class)).collect(Collectors.toList());
        return playerResponse;
    }



    @Override
    public List<GetAllPlayersResponse> getByTrainerName(String name) {
        List<Player>  players=this.playerRepository.getByTrainerName(name);

        List<GetAllPlayersResponse> playerResponse=players.stream() //
                .map(player -> this.modelMapperService.forResponse()
                        .map(player,GetAllPlayersResponse.class)).collect(Collectors.toList());
        return playerResponse;

    }

    @Override
    public List<GetAllPlayersResponse> getAllP(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);  //pageable nesnesini oluşturur. SAYFAYI 0DAN BAŞLATIR O YÜZDEN HER ZAMAN PAGENO -1 YAPARIZ
        List<Player> players=  this.playerRepository.findAll(pageable).getContent();//findAll pageable parametresi alır.
        //getContent pageabli tekrardan entitiesimize dönüştürür

            List<GetAllPlayersResponse> playersResponses = players.stream().map(player -> this.modelMapperService.forResponse()
                    .map(player, GetAllPlayersResponse.class)).collect(Collectors.toList());
            return playersResponses;

    }

    @Override
    public List<GetAllPlayersResponse> getAllSorted() {
        Sort sort= Sort.by(Sort.Direction.DESC,"name");
        List<Player> players=  this.playerRepository.findAll(sort);
        //getContent pageabli tekrardan entitiesimize dönüştürür

        List<GetAllPlayersResponse> playersResponses = players.stream().map(player -> this.modelMapperService.forResponse()
                .map(player,GetAllPlayersResponse.class)).collect(Collectors.toList());
        return playersResponses;
    }

    @Override
    public void update(UpdatePlayerRequest updatePlayerRequest) {
        Player player =  this.modelMapperService.forRequest().map(updatePlayerRequest,Player.class);
        List<PlayersPartner> ppList= playersPartnerImpl.checkIfExistsAnyPlayersPartner(updatePlayerRequest.getContactNumber(), updatePlayerRequest.getPartnerName());
        if(!ppList.isEmpty()){ //eğer herhangi bir sevgilisi avrsa


            player.setPlayersPartner(ppList.get(0)); // ana nesne kaydedilmeden önce ilişkilendirilir
            ppList.get(0).setPlayer(player); //ilikili nesne ilişkilendirilir
            this.playerRepository.save(player);
            this.playersPartnerImpl.update(ppList.get(0),ppList.get(0).getId());
        }
        else{
            PlayersPartner playersPartner = new PlayersPartner(updatePlayerRequest.getPartnerName(),updatePlayerRequest.getContactNumber());
            this.playersPartnerImpl.save(playersPartner); //bu tip ilişkili durumlarda önce ilişkili nesne kaydedilir sonra ana nesne kaydedilir
            player.setPlayersPartner(playersPartner); // ana nesne kaydedilmeden önce ilişkilendirilir
            playersPartner.setPlayer(player); //ilikili nesne ilişkilendirilir
            this.playerRepository.save(player);

        }


    }

    @Override
    public List<GetAllPlayersResponse> getByIdGraterThanOrderById(int comparableNum) {
        List<Player> playerList= this.playerRepository.getByIdGreaterThanOrderById(comparableNum);

        List<GetAllPlayersResponse> playerResponse = playerList.stream().map(player -> this.modelMapperService.forResponse().map(player,GetAllPlayersResponse.class)).collect(Collectors.toList());
        return  playerResponse;
    }


    public List<GetAllPlayersResponse> getTNameAndClub(String trainerName, String clubName) {
          List<Player> playerList = this.playerRepository.getTNameAndClub(trainerName,clubName);
      List<GetAllPlayersResponse> playersResponses = playerList.stream().map(player -> this.modelMapperService.forResponse()
              .map(player,GetAllPlayersResponse.class)).collect(Collectors.toList());
          return playersResponses;


    }
    public void delete(int id){
        this.playerRepository.deleteById(id);
    }
    @Override
    public GetAllPlayersResponse getByPlayersPartnerName(String name) {
        Player player= this.playerRepository.getByPlayersPartnerName(name);

        GetAllPlayersResponse getAllPlayersResponse = modelMapperService.forResponse().map(player,GetAllPlayersResponse.class);

        return getAllPlayersResponse;



    }

}
