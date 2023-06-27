package kbaproject.clubsProject.webApi.Controller;

import kbaproject.clubsProject.Business.abstracts.PlayerService;
import kbaproject.clubsProject.Business.requests.CreatePlayerRequest;
import kbaproject.clubsProject.Business.requests.UpdatePlayerRequest;
import kbaproject.clubsProject.Business.responses.GetAllPlayersResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api"})
@NoArgsConstructor
@AllArgsConstructor
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @GetMapping("/players/getall")
    public List<GetAllPlayersResponse> getAlls(){

        return this.playerService.getAll();
    }

    @PostMapping("/add/players")
    public void adds(@RequestBody CreatePlayerRequest createPlayerRequest){

        this.playerService.adds(createPlayerRequest);
    }
    @PutMapping("/players/{id}")
    public void update(@RequestBody UpdatePlayerRequest updatePlayerRequest){
        this.playerService.update(updatePlayerRequest);
    }

    @DeleteMapping("/players/{id}") //buraya erişmek için bunu kullan demek
    public void deletePl(@PathVariable int id){
        //IOC denilen yapı sayesinde perfromans artar. katmanlardaki new
        this.playerService.delete(id);
    }
    @GetMapping("/players/{name}")
    public List<GetAllPlayersResponse> findByClubName(@PathVariable String name){
        return this.playerService.findByClubName(name);
    }
    @GetMapping("/players/greaterThanNum")
    public ResponseEntity<List<GetAllPlayersResponse>> getByIdGreaterThanOrderById(@RequestParam int greaterThan){

        return new ResponseEntity<>(this.playerService.getByIdGraterThanOrderById(greaterThan), HttpStatus.OK);
    }
    @GetMapping("/players/getbypartnername/{name}")
    public GetAllPlayersResponse findByPartneName(@PathVariable String name){
        return this.playerService.getByPlayersPartnerName(name);
    }
    @GetMapping("/players/getByTrainerNameAndClubName/{trainerName}/{clubname}")
    public List<GetAllPlayersResponse> getByTrainerNameAndClubName(@PathVariable String trainerName, @PathVariable String clubname){
        return this.playerService.getByTrainerNameAndClubName(trainerName,clubname);
    }
    @GetMapping("/players/gettnameandclub/{trainerName}/{clubname}")
    public List<GetAllPlayersResponse> getTNameAndClub(@PathVariable String trainerName, @PathVariable String clubname){
        return this.playerService.getTNameAndClub(trainerName,clubname);
    }
    @GetMapping("/players/gettname/{trainerName}")
    public List<GetAllPlayersResponse> getTName(@PathVariable String trainerName){
        return this.playerService.getByTrainerName(trainerName);
    }
    @GetMapping("/players/getallp/{pageNo}/{pageSize}")
    public ResponseEntity<?> getAllP(@PathVariable int pageNo, @PathVariable int pageSize){
        //hata hata da verebilir sonucunu bilmiyoruz o yüzden <?> yazarız

        return ResponseEntity.ok(this.playerService.getAllP(pageNo,pageSize));
    }
    @GetMapping("/players/getalldesc")
    public List<GetAllPlayersResponse> getAllSorted() {
        return this.playerService.getAllSorted();
    }
}
