package org.fsu.cs.capstone.personalweatherapplication.controllers;
import org.fsu.cs.capstone.personalweatherapplication.models.User;
import org.fsu.cs.capstone.personalweatherapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // FOR TESTING PURPOSES
    @GetMapping("/")
    public String home() {
        // Checks to see if admin user already exists
        // If not, create one and add it to the database.
        Optional<User> user = userRepository.findByUsername("admin");
        if (user.isPresent()) {
            return ("<h1>User already exists..</h1>");
        } else {
            User newUser = new User();
            newUser.setUsername("admin");
            // This step encrypts the password using bcrypt
            newUser.setPassword(passwordEncoder.encode("password"));
            newUser.setRoles("ROLE_ADMIN");
            newUser.setActive(true);
            // Save the user to the database
            userRepository.save(newUser);
            return ("<h1>Created Admin User</h1>");
        }
    }


    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }

    @GetMapping("/dashboard")
    public String dashboard() { return ("<h1>Welcome to PWA Dashboard</h1>");}
}
