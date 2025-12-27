package it.uniroma3.siw_recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw_recipes.model.Credentials;
import it.uniroma3.siw_recipes.service.CredentialsService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CredentialsService credentialsService;

    @GetMapping("/indexAdmin")
    public String index() {
        return "admin/indexAdmin";
    }

    @GetMapping("/users")
    public String manageUsers(Model model) {
        model.addAttribute("credentialsList", this.credentialsService.getAllCredentials());
        return "admin/users";
    }

    @GetMapping("/users/{id}/toggle-status")
    public String toggleUserStatus(@PathVariable("id") Long id) {
        Credentials credentials = this.credentialsService.getCredentials(id);
        if (credentials != null && !credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            credentials.setEnabled(!credentials.isEnabled());
            this.credentialsService.updateCredentials(credentials);
        }
        return "redirect:/admin/users";
    }
}
