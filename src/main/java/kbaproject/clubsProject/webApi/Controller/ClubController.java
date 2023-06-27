package kbaproject.clubsProject.webApi.Controller;

import jakarta.validation.Valid;
import kbaproject.clubsProject.Business.abstracts.ClubService;
import kbaproject.clubsProject.Business.abstracts.PlayerService;
import kbaproject.clubsProject.Business.abstracts.TrainerService;
import kbaproject.clubsProject.Business.requests.CreateClubRequest;
import kbaproject.clubsProject.Business.requests.UpdateClubRequest;
import kbaproject.clubsProject.Business.responses.GetAllClubsResponse;
import kbaproject.clubsProject.Business.responses.GetClubById;
import kbaproject.clubsProject.entities.Club;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api"})
@NoArgsConstructor
@AllArgsConstructor
public class ClubController {
    @Autowired
    private ClubService clubService;

    @GetMapping("/clubs/getall")
    public ResponseEntity<List<GetAllClubsResponse>> getAll() {

        return ResponseEntity.ok(this.clubService.getAll());
    }

    @GetMapping("/clubs/getbytrainer/{trainername}")
    public List<GetAllClubsResponse> getById(@PathVariable String trainername) {
        return clubService.getByTrainerName(trainername);
    }
    @GetMapping("/clubs/getbyplayerid/{id}")
    public GetAllClubsResponse getByPlayerId(@PathVariable int id) {
        return clubService.getByPlayerId(id);
    }
    @GetMapping("/clubs/getbyplayeridwithem/{id}")
    public  GetClubById getByPlayerIdWithEm(@PathVariable int id) {
        return clubService.getByPlayerIdWithEntıtyManager(id);
    }
    @GetMapping("/clubs/playerCount/{id}")
    public  Long countingPlayer(@PathVariable int id) {
        return clubService.playersCounting(id);
    }


    @PostMapping("/add/clubs")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")  //sadece rolü admin olan buraya erişir diyoruz

    public ResponseEntity<?> add(@Valid @RequestBody CreateClubRequest createClubRequest) {
        this.clubService.add(createClubRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/clubs/{id}") //buraya erişmek için bunu kullan demek. Süslü parantez demek variable demek
    public GetClubById getById(@PathVariable int id) { //parhvariabledan al demek git ordaki pathden oku demek
        //IOC denilen yapı sayesinde perfromans artar. katmanlardaki new
        return clubService.getById(id);
    }

    @DeleteMapping("/clubs/{id}") //buraya erişmek için bunu kullan demek
    public void delete(@PathVariable int id) {
        //IOC denilen yapı sayesinde perfromans artar. katmanlardaki new
        this.clubService.delete(id);
    }

    @PutMapping("/clubs/{id}")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public void update(@RequestBody UpdateClubRequest updateClubRequest) {
        this.clubService.update(updateClubRequest);
    }

    @GetMapping("/clubs/getClubBetween/{a}/{b}") //buraya erişmek için bunu kullan demek. Süslü parantez demek variable demek
    public List<GetAllClubsResponse> getByClubBetween(@PathVariable int a,@PathVariable int b) { //parhvariabledan al demek git ordaki pathden oku demek
        return this.clubService.getByTrophiesCountBetween(a,b);
    }
}