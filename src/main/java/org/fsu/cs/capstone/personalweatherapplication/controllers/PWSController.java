package org.fsu.cs.capstone.personalweatherapplication.controllers;

import org.fsu.cs.capstone.personalweatherapplication.models.PWSData;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class PWSController {

    @PostMapping ("/data/report")
    public void greetingJsonPost(PWSData pwsData) throws Exception {

        System.out.println(pwsData);

    }
}
