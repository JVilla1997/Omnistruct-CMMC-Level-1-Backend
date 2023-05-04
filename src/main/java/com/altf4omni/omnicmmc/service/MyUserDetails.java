package com.altf4omni.omnicmmc.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class MyUserDetails implements UserDetails{

//    private final UserRepository userRepository;
//
//    public MyUserDetails(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        //Set<Role> roles = user.getRoles();
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return userRepository.toString();
//        //userRepository.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return userRepository.toString();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    /**
//     * WILL NEED TO RETURN CORRECT THING; HAVE FALSE FOR NOW
//     * @return
//     */
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }

    private final User user;

    public MyUserDetails(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<Role> roles = user.getRoles();
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (Role role : roles) {
        return user.getAuthorities();
//        }
//        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
        //userRepository.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
        return user.isEnabled();
    }

}