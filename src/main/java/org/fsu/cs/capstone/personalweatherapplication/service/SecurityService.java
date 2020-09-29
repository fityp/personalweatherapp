package org.fsu.cs.capstone.personalweatherapplication.service;


// SecurityService provides current logged-in user and auto login after user registration.
public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
