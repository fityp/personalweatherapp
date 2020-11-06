package org.fsu.cs.capstone.personalweatherapplication.controllers;
import org.fsu.cs.capstone.personalweatherapplication.models.User;
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

        Optional<User> theUser = userRepository.findByUsername(username);
        if (theUser.isPresent()) {
            bindingResult.reject("username");
            redir.addFlashAttribute("errorMessage", "That Username already exists");
            return "redirect:/register";
        } else {
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

    @GetMapping("/checkPasskey")
    public String checkPasskey() {
        // TODO
        // 1.) Get the id of current user
        // 2.) Check UserStationMapping model to see if user has a station id
        // 3.) If they do, go to /dashboard
        // 4.) If they don't go to /passkey
        boolean passkey = false;
        if (passkey) {
            return "redirect:/dashboard";
        } else {
            return "redirect:/passkey";
        }
    }

    @PostMapping("/processPasskey")
    public String processPasskey(@RequestParam(name="passkey") Integer passkey) {
        // TODO
        // 1.) Get the id of the current user
        // 2.) Search through UserStationMapping model for the id
        // 3.) Set the given passkey to that specific id
        // 4.) Continue to dashboard
        System.out.println("Passkey is: " + passkey);
        return "redirect:/dashboard";
    }

    @GetMapping("/passkey")
    public String passkey() {
        return "passkey";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        model.addAttribute("username", username);
        return "dashboard";
    }
}
