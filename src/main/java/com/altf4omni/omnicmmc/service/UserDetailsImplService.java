package com.altf4omni.omnicmmc.service;

import com.altf4omni.omnicmmc.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;

@Service
public class UserDetailsImplService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException
            {
                User user = userRepository.getUserByUsername(username);

                if(user == null)
                {
                    throw new UsernameNotFoundException("Could not find user");
                }

                return new MyUserDetails(user);
            }
}
