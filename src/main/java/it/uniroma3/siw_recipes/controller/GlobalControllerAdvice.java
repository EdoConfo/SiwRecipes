package it.uniroma3.siw_recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import it.uniroma3.siw_recipes.model.Credentials;
import it.uniroma3.siw_recipes.model.User;
import it.uniroma3.siw_recipes.service.CredentialsService;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private CredentialsService credentialsService;

    @ModelAttribute("currentUser")
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        Credentials credentials = credentialsService.getCredentials(username);
        if (credentials != null) {
            return credentials.getUser();
        }
        
        return null;
    }
}
