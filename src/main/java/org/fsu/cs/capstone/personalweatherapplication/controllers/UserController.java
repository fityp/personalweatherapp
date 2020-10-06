package org.fsu.cs.capstone.personalweatherapplication.controllers;
import org.fsu.cs.capstone.personalweatherapplication.models.User;
import org.fsu.cs.capstone.personalweatherapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user){
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/register")
    public String processRegistration(BindingResult bindingResult,
                                      @RequestParam Map<String,String> requestParams,
                                      RedirectAttributes redir) {
        String username = requestParams.get("username");
        String password = requestParams.get("password");

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
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
    public ModelAndView showLoginPage(ModelAndView modelAndView, User user) {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
