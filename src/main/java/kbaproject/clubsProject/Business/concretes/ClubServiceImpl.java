package kbaproject.clubsProject.Business.concretes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import kbaproject.clubsProject.Business.abstracts.ClubService;
import kbaproject.clubsProject.Business.abstracts.PlayerService;
import kbaproject.clubsProject.Business.responses.GetAllClubsResponse;
import kbaproject.clubsProject.Business.responses.GetAllPlayersResponse;
import kbaproject.clubsProject.Business.responses.GetClubById;
import kbaproject.clubsProject.Business.requests.CreateClubRequest;
import kbaproject.clubsProject.Business.requests.UpdateClubRequest;
import kbaproject.clubsProject.Business.rules.ClubBusinessRules;
import kbaproject.clubsProject.DataAccess.abstracts.ClubRepository;
import kbaproject.clubsProject.core.utilities.exceptions.BusinessException;
import kbaproject.clubsProject.core.utilities.mappers.ModelMapperService;
import kbaproject.clubsProject.entities.Club;
import kbaproject.clubsProject.entities.Player;
import kbaproject.clubsProject.entities.President;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ClubServiceImpl implements ClubService { //iş kuralları burada yazılır
    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private ModelMapperService modelMapperService;
    @Autowired
    private ClubBusinessRules clubBusinessRules;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private EntityManager entityManager;


    @Override
    public List<GetAllClubsResponse> getAll() {
        this.clubBusinessRules.checkIfExistsAnyClub();

        List<Club> clubs=clubRepository.findAll(); //getAllClubsResponses tipinde döndürmem lazıma ama findAll metodu ile nesnelerimin hepsini bir dizide tut

        List<GetAllClubsResponse> clubsResponse=clubs.stream() //her bir clubs için
                .map(brand -> this.modelMapperService.forResponse()
                        .map(brand,GetAllClubsResponse.class)).collect(Collectors.toList()); //ilk map stream api'ın mapidir.eldeki listeyi tek tek dolaşmayı sağlar
        //her bir brand için modelservicein mapini kullaanrak mapleme yap
        //tolist metoduyla listeye çevirir
        return clubsResponse ;


    }

    @Override
    public GetClubById getById(int id) {
        Club club=this.clubRepository.findById(id).orElseThrow(()-> new BusinessException("Bu idde takım bulunamadı"));

        GetClubById response= this.modelMapperService.forResponse().map(club,GetClubById.class);


        return response;

    }

    @Override
    public void add(CreateClubRequest createClubRequest) {
        this.clubBusinessRules.checkIfClubNameExists(createClubRequest.getName());//bu şekilde gereken kuralları alt alta yazacağız
       // this.clubBusinessRules.checkIfTrophiesCountsExists(createClubRequest.getTrophiesCount());

        Club club = this.modelMapperService.forRequest().map(createClubRequest,Club.class);

        President president = new President(createClubRequest.getPresidentName(),createClubRequest.getPresidentEmail());
        club.setPresident(president);
        president.setClub(club);

        this.clubRepository.save(club);

    }

    @Override
    public void delete(int id) {
        this.clubRepository.deleteById(id);

    }

    @Override
    public void update(UpdateClubRequest updateClubRequest) {
        Club club= this.modelMapperService.forRequest().map(updateClubRequest,Club.class);
        this.clubRepository.save(club);

    }

    @Override
    public List<GetAllClubsResponse> getByTrainerName(String trainerName) {
        List<GetAllPlayersResponse> players = this.playerService.getByTrainerName(trainerName);

        List<Integer> playerIds=players.stream().map(p->p.getPlayerId()).collect(Collectors.toList());

        List<Club> clubs=this.clubRepository.getByPlayers_idIn(playerIds);

        List<GetAllClubsResponse> returnClubs= clubs.stream()
                .map(club -> this.modelMapperService.forResponse().map(club,GetAllClubsResponse.class)).collect(Collectors.toList());
        return returnClubs;

    }

    @Override
    public List<GetAllClubsResponse> getByTrophiesCountBetween(int a, int b) {
        List<Club> clubs= this.clubRepository.getByTrophiesCountBetween(a,b);
        List<GetAllClubsResponse> dondurulecek= clubs.stream().map(club -> this.modelMapperService.forResponse().map(club,GetAllClubsResponse.class)).collect(Collectors.toList());
    return dondurulecek;
    }

    @Override
    public GetAllClubsResponse getByPlayerId(int id) {
      Club clubs=  this.clubRepository.findByPlayersId(id);
      GetAllClubsResponse response= this.modelMapperService.forResponse().map(clubs,GetAllClubsResponse.class);
      return response;
    }

    //entity manager ile yapılışı
    public GetClubById getByPlayerIdWithEntıtyManager(int id) {
        TypedQuery<Club> query= entityManager.createQuery("select c from Club c JOIN FETCH c.players p where p.id=:data",Club.class);
        query.setParameter("data",id);
        Club club= query.getSingleResult();
        GetClubById response =this.modelMapperService.forResponse().map(club,GetClubById.class);
        return response;
    }
    @Override
    public Long playersCounting(int id) {
        //BİR TAKIMDA KAÇ OYUNCU VAR ONUN SAYISINI VERİR
        Long query= entityManager.createQuery("select COUNT(p) from Club c JOIN  c.players p on c.id=p.club where c.id=:data",Long.class)
                .setParameter("data",id)
                .getSingleResult();
        return query;
    }

}
