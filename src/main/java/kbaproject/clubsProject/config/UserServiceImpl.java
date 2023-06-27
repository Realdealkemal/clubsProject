package kbaproject.clubsProject.config;

import kbaproject.clubsProject.config.UserService;
import kbaproject.clubsProject.core.dataAccess.UserRepository;
import kbaproject.clubsProject.core.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); //öncelikle passwordu encode ederek şifreleriz
        this.userRepository.save(user); //sonra sisteme kaydederiz
        return "user added to system";

    }
}
