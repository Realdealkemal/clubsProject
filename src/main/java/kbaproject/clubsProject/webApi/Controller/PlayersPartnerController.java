package kbaproject.clubsProject.webApi.Controller;

import kbaproject.clubsProject.Business.concretes.PlayersPartnerImpl;
import kbaproject.clubsProject.Business.requests.CreatePPartnerRequest;
import kbaproject.clubsProject.Business.responses.GetAllTrainersResponse;
import kbaproject.clubsProject.entities.PlayersPartner;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/partners"})
@AllArgsConstructor
@NoArgsConstructor
public class PlayersPartnerController {
    @Autowired
    private PlayersPartnerImpl playersPartnerImpl;
    @PostMapping("/add")
    public void add(@RequestBody PlayersPartner playersPartner){
        playersPartnerImpl.save(playersPartner);

    }
    @GetMapping("/{id}")
    public PlayersPartner findById(@PathVariable int id){
        return  playersPartnerImpl.findById(id);

    }
    @GetMapping("/getbyname/{name}")
    public List<PlayersPartner> getByName(@PathVariable String name){
        return  playersPartnerImpl.getByName(name);

    }
    @DeleteMapping("/delete/{id}")
    public void del(@PathVariable int id){
        playersPartnerImpl.delete(id);

    }
    @GetMapping("/getall")
    public List<PlayersPartner> getAll(){
       return playersPartnerImpl.getAll();
    }

    @GetMapping("/getbyplayer/{id}")
    public PlayersPartner getByPlayer(@PathVariable int id){
        return  playersPartnerImpl.getByPlayerId(id);

    }
    @GetMapping("/getppclubname/{clubName}")
    public List<PlayersPartner> getByClubName(@PathVariable String clubName) {

        return this.playersPartnerImpl.getByClubsName(clubName);
    }

    @PutMapping("/update/{id}")
    public void updatePartner(@RequestBody PlayersPartner playersPartner,@PathVariable int id){
        playersPartnerImpl.update(playersPartner,id);

    }
    @GetMapping("/getbylimit")
    public List<PlayersPartner> getByLimitPartners(){
        return playersPartnerImpl.listsTop3PlayersPartner();
    }

    @GetMapping("/pageable/{pageNo}/{pageSize}")
    public List<PlayersPartner> pageablePartners(@PathVariable int pageNo, @PathVariable int pageSize){
        return playersPartnerImpl.pageablePartners(pageNo,pageSize);
    }

    @GetMapping("/getbyavgNameCeyseandJennifer/{name}/{name2}")
    public List<PlayersPartner> getByAvgCeyseAndJennifer(@PathVariable String name,@PathVariable String name2){
        return playersPartnerImpl.getAvgNameJenniferandCeyse(name,name2);
    }

    @GetMapping("/getByTeamName/{teamName}")
    public List<PlayersPartner> getByTeamName(@PathVariable String teamName){
        return playersPartnerImpl.getByClubsName(teamName);
    }

    @GetMapping("/getContactNumberGreater/{parameter}")
    public List<PlayersPartner> getByTeamName(@PathVariable int parameter){
        return playersPartnerImpl.contactNumberCharacterGreater(parameter);
    }

}
