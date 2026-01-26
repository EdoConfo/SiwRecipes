package it.uniroma3.siw_recipes.controller;

// ...existing code...

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw_recipes.model.Credentials;
import it.uniroma3.siw_recipes.model.User;
import it.uniroma3.siw_recipes.service.CredentialsService;
import jakarta.validation.Valid;

/**
 * AuthenticationController gestisce le operazioni di Login e Registrazione.
 */
@Controller
public class AuthenticationController {
    @Autowired
    private CredentialsService credentialsService;

    // userService non più necessario

    @Autowired
    private it.uniroma3.siw_recipes.service.RecipeService recipeService;

    @Autowired
    private it.uniroma3.siw_recipes.service.ReviewService reviewService;

    // Registrazione
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        return "formRegisterUser";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult userBindingResult,
                               @Valid @ModelAttribute("credentials") Credentials credentials,
                               BindingResult credentialsBindingResult,
                               Model model) {
        if (!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            model.addAttribute("user", user);
            return "registrationSuccessful";
        }
        return "formRegisterUser";
    }

    // Login
    @GetMapping("/login")
    public String showLoginForm() {
        return "formLogin";
    }

    // Home
    @GetMapping("/")
    public String index(Model model) {
        List<it.uniroma3.siw_recipes.model.RecipeSummary> latestRecipes = this.recipeService.getLastRecipes();
        model.addAttribute("latestRecipes", latestRecipes);

        // Calcolo media recensioni per ogni ricetta
        java.util.Map<Long, String> averageRatings = new java.util.HashMap<>();
        for (it.uniroma3.siw_recipes.model.RecipeSummary recipe : latestRecipes) {
            List<it.uniroma3.siw_recipes.model.Review> reviews = this.reviewService.getReviewsByRecipe(this.recipeService.getRecipe(recipe.getId()));
            double avg = 0.0;
            if (reviews != null && !reviews.isEmpty()) {
                double sum = 0.0;
                for (it.uniroma3.siw_recipes.model.Review r : reviews) {
                    if (r.getRating() != null) sum += r.getRating();
                }
                avg = sum / reviews.size();
            }
            averageRatings.put(recipe.getId(), String.format("%.1f", avg));
        }
        model.addAttribute("averageRatings", averageRatings);
        return "home";
    }

    // Redirect dopo login
    @GetMapping("/success")
    public String defaultAfterLogin() {
        return "redirect:/";
    }
}
