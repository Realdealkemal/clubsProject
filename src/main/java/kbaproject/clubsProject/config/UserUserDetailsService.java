package kbaproject.clubsProject.config;

import kbaproject.clubsProject.core.dataAccess.UserRepository;
import kbaproject.clubsProject.core.entities.User;
import kbaproject.clubsProject.core.utilities.mappers.ModelMapperService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;


@Component
@NoArgsConstructor
@AllArgsConstructor
public class UserUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //kullanıcın kimlik bilgilerini veri tabanından burda çekeriz
        Optional<User> user= this.userRepository.findByName(username);
     return user.map(u->new UserUserMapperUserDetails(u)) //gelen user verilerini UsserUserMapper sınıfı ile userDetailse dönüştürürüz
                .orElseThrow(() -> new UsernameNotFoundException("user not found" + username));
        // UserDetails u= user.stream().map(user1 -> this.modelMapperService.forResponse().map(user1,UserDetails.class)).collect(Collectors.toList());





    }
}
