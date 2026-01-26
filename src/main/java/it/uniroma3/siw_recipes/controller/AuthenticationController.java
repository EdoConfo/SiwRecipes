package it.uniroma3.siw_recipes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw_recipes.model.Credentials;
import it.uniroma3.siw_recipes.model.User;
import it.uniroma3.siw_recipes.service.CredentialsService;
import it.uniroma3.siw_recipes.service.UserService;
import jakarta.validation.Valid;

/**
 * AuthenticationController gestisce le operazioni di Login e Registrazione.
 */
@Controller
public class AuthenticationController {
	
	@Autowired
	private CredentialsService credentialsService;

    @Autowired
	private UserService userService;
    
    @Autowired
    private it.uniroma3.siw_recipes.service.RecipeService recipeService;

    @Autowired
    private it.uniroma3.siw_recipes.service.ReviewService reviewService;
	
	@GetMapping(value = "/register") 
	public String showRegisterForm (Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		return "formRegisterUser";
	}
	
	@GetMapping(value = "/login") 
	public String showLoginForm (Model model) {
		return "formLogin";
	}

	@GetMapping(value = "/") 
	public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        java.util.List<it.uniroma3.siw_recipes.model.RecipeSummary> latestRecipes = this.recipeService.getLastRecipes();
        model.addAttribute("latestRecipes", latestRecipes);

        // Calcolo media recensioni per ogni ricetta
        java.util.Map<Long, String> averageRatings = new java.util.HashMap<>();
        for (it.uniroma3.siw_recipes.model.RecipeSummary recipe : latestRecipes) {
            java.util.List<it.uniroma3.siw_recipes.model.Review> reviews = this.reviewService.getReviewsByRecipe(this.recipeService.getRecipe(recipe.getId()));
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
    
    @GetMapping(value = "/profile")
    public String showProfile(Model model) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
        User user = credentials.getUser();

        List<it.uniroma3.siw_recipes.model.Recipe> userRecipes = recipeService.getRecipesByAuthor(user);
        java.util.Map<Long, String> averageRatings = new java.util.HashMap<>();
        double sumOfAverages = 0.0;
        int recipesWithReviews = 0;
        for (it.uniroma3.siw_recipes.model.Recipe recipe : userRecipes) {
            java.util.List<it.uniroma3.siw_recipes.model.Review> reviews = this.reviewService.getReviewsByRecipe(recipe);
            double avg = 0.0;
            if (reviews != null && !reviews.isEmpty()) {
                double sum = 0.0;
                for (it.uniroma3.siw_recipes.model.Review r : reviews) {
                    if (r.getRating() != null) sum += r.getRating();
                }
                avg = sum / reviews.size();
                sumOfAverages += avg;
                recipesWithReviews++;
            }
            averageRatings.put(recipe.getId(), String.format("%.1f", avg));
        }
        double profileAverageRating = recipesWithReviews > 0 ? sumOfAverages / recipesWithReviews : 0.0;
        model.addAttribute("profileAverageRating", String.format("%.1f", profileAverageRating));

        // Recensioni fatte dall'utente
        java.util.List<it.uniroma3.siw_recipes.model.Review> userReviews = user.getReviews();

        model.addAttribute("user", user);
        model.addAttribute("recipes", userRecipes);
        model.addAttribute("averageRatings", averageRatings);
        model.addAttribute("userReviews", userReviews);
        return "userProfile";
    }
		
    @GetMapping(value = "/success")
    public String defaultAfterLogin(Model model) {
        return "redirect:/";
    }

	@PostMapping(value = { "/register" })
    public String registerUser(@Valid @ModelAttribute("user") User user,
                 BindingResult userBindingResult, @Valid
                 @ModelAttribute("credentials") Credentials credentials,
                 BindingResult credentialsBindingResult,
                 Model model) {

		// Se user e credentials sono validi
        if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            model.addAttribute("user", user);
            return "registrationSuccessful";
        }
        return "formRegisterUser";
    }
}
