package org.fsu.cs.capstone.personalweatherapplication.controllers;
import org.fsu.cs.capstone.personalweatherapplication.models.Station;
import org.fsu.cs.capstone.personalweatherapplication.models.User;
import org.fsu.cs.capstone.personalweatherapplication.models.StationData;
import org.fsu.cs.capstone.personalweatherapplication.repositories.StationRepository;
import org.fsu.cs.capstone.personalweatherapplication.repositories.UserRepository;
import org.fsu.cs.capstone.personalweatherapplication.repositories.StationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Date;
import java.util.Calendar; 
import java.util.List;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StationRepository stationRepository;
    
    @Autowired
    private StationDataRepository stationDataRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    long DAY_IN_MS = 1000 * 60 * 60 * 24;
    //Date date1 = new Date(System.currentTimeMillis() - (4 * DAY_IN_MS)); //offset these two to match Dustins test data from current date i.e. if today is the 16th then set to 4 and 3 to get his data, which was recorded on the 13th
    //Date date2 = new Date(System.currentTimeMillis() - (3 * DAY_IN_MS)); //also, with current data the only offset would be 1 on the first line
    //Calendar cDate =  Calendar.getInstance();

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
            

            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setRoles("ROLE_USER");
            newUser.setActive(true);
            newUser.setPasskey(passkey);
            userRepository.save(newUser);
            Station station = Station.builder().passkey(passkey).stationUser(newUser).build();
            stationRepository.save(station);
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
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Station> station = stationRepository.findByPasskey(user.get().getPasskey());
        StationData stationData = stationDataRepository.findFirstByStationOrderByGetheredDateDesc(station.get());
        Date stationDate = stationData.getGetheredDate();
        List<StationData> hourlyStationData = stationDataRepository.findByGetheredDateBetween(new Date(stationDate.getTime() - DAY_IN_MS), stationDate);
        if (stationData == null) {stationData = new StationData();} //create default stationData object if none exist in database to avoid dashboard error
        
       
        //cDate.setTime(stationDate);
        
        //int day = cDate.get(Calendar.DAY_OF_MONTH);
        float[] hours = new float[24]; //index = hour in military time
        //hours[0] = 0;
        
        for(StationData data: hourlyStationData)
        {
           if(data.getStation().getPasskey() == station.get().getPasskey()) { hours[(int)(data.getGetheredDate().getTime() % 86400000) / 3600000] = data.getTempf(); } //set temp for appropriate hour for approriate station modulus and / converts miliseconds to hour
        }
        
        model.addAttribute("username", username);
        model.addAttribute("stationData", stationData);
        model.addAttribute("hours", hours);
        
        return "dashboard";
    }
}
