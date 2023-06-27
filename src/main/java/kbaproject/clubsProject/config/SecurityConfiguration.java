package kbaproject.clubsProject.config;

import kbaproject.clubsProject.DataAccess.abstracts.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity //istek geldiğinde config drumunu kendine göre ayarlar ve devreey sokar
@Configuration
@EnableMethodSecurity
public class SecurityConfiguration  {
    @Autowired
    private UserUserDetailsService userUserDetailsService;


    @Bean //authentication
    public UserDetailsService userDetailsManager(/*PasswordEncoder encoder*/){
        //UserDetailsService arayüzü, kullanıcının kimlik bilgilerini sağlamak için kullanılan bir arayüzdür
        /*UserDetails user= User
                .withUsername("user")
                .password(encoder.encode("user"))
                .roles("USER")
                .build();

        UserDetails admin= User
                .withUsername("admin")
                .password(encoder.encode("admin")) //encoder sınıfı şifreyi gizlemeye yarar

                .roles("ADMIN")
                .build(); //burayı yoruma alırım çünkü biz in memory değil databaseda saklayacağız
        return InMemoryUserDetailsManager(admin,user);*/ //admin ve user nesnelerini in memorye ekleriz
        return userUserDetailsService; //kullanıcının kimlik bilgilerini veri tabanından çekerek kullanılabilir hale getirir

    }
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        return http.csrf().disable()
             .authorizeHttpRequests()
             .requestMatchers("/index","/api/players/**","/api/clubs/**","/api/add/**","/new","api/players/gettnameandclub/**","api/trainers/**","partners/**","api/presidents/**","/rabbit/**").permitAll()
             .and()

             .authorizeHttpRequests().requestMatchers("/dashboard").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .requestMatchers("/admin").hasAuthority("ROLE_ADMIN")
        .and().formLogin()
             .and().build();

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        //kullanıcının kimlik bilgilerini doğrulama sürecinin özelleşmesini sağlar
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsManager());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


}
