package kbaproject.clubsProject.webApi.Controller;

import kbaproject.clubsProject.config.UserService;
import kbaproject.clubsProject.core.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/admin")
    public String admin(){
        return "Admine özel mesajdır";
    }

    @GetMapping("/index")
    public String index(){
        return "Index,Sayfası hoşgeldiniz";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "login başarılı dashboard sayfası";
    }

    @PostMapping("/new")
    public String addUser(@RequestBody User user){
       return this.userService.addUser(user);


    }
}
