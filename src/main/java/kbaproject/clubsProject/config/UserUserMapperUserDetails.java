package kbaproject.clubsProject.config;

import kbaproject.clubsProject.core.entities.User;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class UserUserMapperUserDetails implements UserDetails { //gelen verileri User verilerini Userdetailse dönüştürmem lazım
    private String name;
    private String password;
    private List<GrantedAuthority> authorities; //rolleri bu şekilde tutarız
    public UserUserMapperUserDetails(User user){
        this.name=user.getName();
        this.password=user.getPassword();
       this. authorities= Arrays.stream(user.getRoles().split(","))
                .map(u->new SimpleGrantedAuthority(u)).collect(Collectors.toList());

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
