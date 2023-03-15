package com.altf4omni.omnicmmc.service;
import com.altf4omni.omnicmmc.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MyUserDetails implements UserDetails{

    private final UserRepository userRepository;

    public MyUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

      /*  for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }*/

        return authorities;
    }

    @Override
    public String getPassword() {
        return userRepository.toString();
        //userRepository.getPassword();
    }

    @Override
    public String getUsername() {
        return userRepository.toString();
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

    /**
     * WILL NEED TO RETURN CORRECT THING; HAVE FALSE FOR NOW
     * @return
     */
    @Override
    public boolean isEnabled() {
        return false;
    }

}