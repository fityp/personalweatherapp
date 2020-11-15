package org.fsu.cs.capstone.personalweatherapplication.controllers;
import org.fsu.cs.capstone.personalweatherapplication.models.Station;
import org.fsu.cs.capstone.personalweatherapplication.models.User;
import org.fsu.cs.capstone.personalweatherapplication.repositories.StationRepository;
import org.fsu.cs.capstone.personalweatherapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/register")
    public String showRegisterPage(Model theModel){
        theModel.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/processRegistration")
    public String processRegistration(@ModelAttribute("user") User user,
                                      BindingResult bindingResult,
                                      RedirectAttributes redir) {

        String username = user.getUsername();
        String password = user.getPassword();
        String passkey = user.getPasskey();

        Optional<User> theUser = userRepository.findByUsername(username);
        Optional<Station> ostation =  stationRepository.findByPasskey(passkey);
        if (theUser.isPresent() || ostation.isPresent()) {
            if (theUser.isPresent()) bindingResult.reject("username");
            if (ostation.isPresent()) bindingResult.reject("passkey");
            redir.addFlashAttribute("errorMessage", "That Username or Station PASSKEY already exists");
            return "redirect:/register";
        } else {
            Station station = Station.builder().passkey(passkey).build();
            stationRepository.save(station);

            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setRoles("ROLE_USER");
            newUser.setActive(true);
            userRepository.save(newUser);
            redir.addFlashAttribute("successMessage", "User has been created!");
            return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }


    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        model.addAttribute("username", username);
        return "dashboard";
    }
}
