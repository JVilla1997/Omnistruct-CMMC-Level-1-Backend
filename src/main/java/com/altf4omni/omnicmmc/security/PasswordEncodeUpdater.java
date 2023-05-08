package com.altf4omni.omnicmmc.security;

import com.altf4omni.omnicmmc.entity.User;
import com.altf4omni.omnicmmc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncodeUpdater implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user = userRepository.findByUsername("admin"); // replace with the actual username
        if (user != null && !user.isPasswordUpdated()) {
            String encodedPassword = passwordEncoder.encode(user.getPassword()); // replace with the new password
            user.setPassword(encodedPassword);
            user.setPasswordUpdated(true); // set the flag to indicate that the password has been updated
            userRepository.save(user);
        }
    }
}
