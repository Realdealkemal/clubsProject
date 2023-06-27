package kbaproject.clubsProject.webApi.Controller;

import kbaproject.clubsProject.Business.abstracts.PresidentService;
import kbaproject.clubsProject.Business.responses.GetAllPresidentResponse;
import kbaproject.clubsProject.entities.President;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/api"})
@NoArgsConstructor
@AllArgsConstructor
public class PresidentController {
    @Autowired
    private PresidentService presidentService;

    @GetMapping("/presidents/getall")
    public List<GetAllPresidentResponse> getAll(){
        return  this.presidentService.getAll();

    }
}
