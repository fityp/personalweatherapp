package org.fsu.cs.capstone.personalweatherapplication.security;


import org.fsu.cs.capstone.personalweatherapplication.models.UserDetailsImpl;
import org.fsu.cs.capstone.personalweatherapplication.models.User;
import org.fsu.cs.capstone.personalweatherapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find user in database
        Optional<User> user = userRepository.findByUsername(username);

        // Throw error is user not found
        user.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + username));

        // If user found, return the user details
        return user.map(UserDetailsImpl::new).get();
    }
}
