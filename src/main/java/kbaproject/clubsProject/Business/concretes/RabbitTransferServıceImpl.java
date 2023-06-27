package kbaproject.clubsProject.Business.concretes;

import kbaproject.clubsProject.Business.requests.TransferRequest;
import kbaproject.clubsProject.Business.requests.UpdatePlayerRequest;
import kbaproject.clubsProject.Business.responses.GetAllPlayersResponse;
import kbaproject.clubsProject.DataAccess.abstracts.ClubRepository;
import kbaproject.clubsProject.DataAccess.abstracts.PlayerRepository;
import kbaproject.clubsProject.core.utilities.exceptions.BusinessException;
import kbaproject.clubsProject.core.utilities.mappers.ModelMapperService;
import kbaproject.clubsProject.entities.Club;
import kbaproject.clubsProject.entities.Player;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RabbitTransferServıceImpl {


    private final ClubRepository clubRepository;
    private final PlayerRepository playerRepository;
    private final ModelMapperService modelMapperService;
    private final PlayerServiceImpl playerService;
    private final ClubServiceImpl clubService;


    private final DirectExchange exchange; //RabbitmqConfiguredaki beandan gelir.

    private final AmqpTemplate rabbitTemplate; //queuemize değer yazabilmemiz için kullanır. rabbitmqye verdiğimiz objeyi jsona çevirir

    @Value("firstRoute")
    String routingKey;

    @Value("firstStepQueue")
    String queueName;

    public RabbitTransferServıceImpl(DirectExchange exchange, AmqpTemplate rabbitTemplate, ClubRepository clubRepository, PlayerRepository playerRepository,ModelMapperService modelMapperService,PlayerServiceImpl playerService,ClubServiceImpl clubService) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.clubRepository = clubRepository;
        this.playerRepository = playerRepository;
        this.modelMapperService=modelMapperService;
        this.playerService=playerService;
        this.clubService=clubService;
    }

    public void transferPlayer(TransferRequest transferRequest) {

        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, transferRequest);
        //burada aşdığımız transfer isteğini direkt olarak rabbitmqya yazarız.
    }

    @RabbitListener(queues = "firstStepQueue") // transferPlayer metondu rabbitmqya yazmaya başladıktan sonra. ilk oluşturduğumuz kuyruğu dinler
    public void TransferPlayerMesaj(TransferRequest transferRequest) {
        //böyle bir kulüp Idmız var mı diye bakarız
        Optional<Club> clubOptional = clubRepository.findById(transferRequest.getFromClubId());
        clubOptional.ifPresentOrElse(club -> { //böyle bir club idmız var ama bu clubte o idli oyuncu oynuyor mu diye bakarız
                    if (club.getId() == clubRepository.findByPlayersId(transferRequest.getPlayerId()).getId()) {
                        Player transferPlayer = playerRepository.findById(transferRequest.getPlayerId()).orElseThrow(()-> new BusinessException("Bu oyuncu burada oynamıyor"));
                        System.out.println("BU oyuncu "+transferPlayer.getClub().getName()+" takımından transfer oluyor");


                        UpdatePlayerRequest response= this.modelMapperService.forResponse().map(transferPlayer, UpdatePlayerRequest.class);
                        response.setClubId(16); //oyuncuyu free agents takımına atadım
                        playerService.update(response); //free agentsa kaydettim

                       // Bu 3 kod bloğunun normal şartlarda hiçbir anlammı yok ama ben burada ilerki mesajlaşmalarda sıkıntı olursa diye tutuyorum.
                       // yaptığım işlemi geri alma testi için başta oyuncunun değerini free agents diye bir takıma atıyorum. eğer 2. queuede bulamazsa geri eski haline çeviriyorum


                        rabbitTemplate.convertAndSend(exchange.getName(), "secondRoute", transferRequest);
                         //RabbitMq dünyası asenktron bir dünyadır bir sonraki iaşamalarda sıkıntı olduğunda geri alabilmek için transferRequest bodysini de yolluyoruz
                        //burada yeni bir producer üretiyoruz
                    } else {
                        System.out.println("Bu oyuncu belirttiğiniz kulupte oynamıyor");
                    }
                },
                () -> System.out.println("Belirttiğiniz kulüp bulunamadı")
                //DİREKT BÖYLE BİR TAKIM YOKSA
        );


    }

    @RabbitListener(queues = "secondStepQueue")
    public void finalizeTransfer(TransferRequest transferRequest){
        Player transferPlayer = playerRepository.findById(transferRequest.getPlayerId()).orElseThrow(()-> new BusinessException("Böyle bir oyuncu yok"));
        UpdatePlayerRequest response= this.modelMapperService.forResponse().map(transferPlayer, UpdatePlayerRequest.class);

        Optional<Club> clubOptional = clubRepository.findById(transferRequest.getToClubId()); //oyuncunun gideceği takım diye bir takım var mı bakarız

        clubOptional.ifPresentOrElse(club -> {
            if(clubService.playersCounting(club.getId())<(int) 18){
                response.setClubId(transferRequest.getToClubId());
                playerService.update(response);
                System.out.println("Bu oyuncu "+response.getClubId()+" takımına transfer oldu");


            }
            else{
                response.setClubId(transferRequest.getFromClubId());
                playerService.update(response);
                System.out.println("BU takım oyuncu satmadan oyuncu alamaz");
            }
        },
                ()-> {
                    System.out.println("Oyuncunun gideceği takımı yanlış girdiniz");
                    response.setClubId(transferRequest.getFromClubId());
                    playerService.update(response);
                }
              );

    }
}
