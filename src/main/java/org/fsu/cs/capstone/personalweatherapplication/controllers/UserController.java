package org.fsu.cs.capstone.personalweatherapplication.controllers;
import org.fsu.cs.capstone.personalweatherapplication.models.User;
import org.fsu.cs.capstone.personalweatherapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home() {
        User newUser = new User();
        newUser.setUsername("devin");
        newUser.setPassword("password");
        newUser.setRoles("ADMIN");
        newUser.setActive(true);
        userRepository.save(newUser);
        return "greeting";
    }


    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }
}
