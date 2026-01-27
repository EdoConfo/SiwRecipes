package it.uniroma3.siw_recipes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class UserController {

	@Autowired
	private CredentialsService credentialsService;

	@Autowired
	private UserService userService;

	@Autowired
	private it.uniroma3.siw_recipes.service.RecipeService recipeService;

	@Autowired
	private it.uniroma3.siw_recipes.service.ReviewService reviewService;

	@GetMapping("/profile/edit")
	public String showEditAccountForm(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		User user = credentials.getUser();
		model.addAttribute("user", user);
		model.addAttribute("credentials", credentials);
		return "formEditAccount";
	}

	@PostMapping("/profile/edit")
	public String editAccount(@ModelAttribute("user") @Valid User user, BindingResult userBindingResult,
							  @ModelAttribute("credentials") Credentials credentialsForm, BindingResult credentialsBindingResult,
							  Model model) {
		if (userBindingResult.hasErrors() || credentialsBindingResult.hasErrors()) {
			model.addAttribute("credentials", credentialsForm);
			return "formEditAccount";
		}
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		User currentUser = credentials.getUser();
		currentUser.setName(user.getName());
		currentUser.setSurname(user.getSurname());
		currentUser.setEmail(user.getEmail());

		// Aggiorna username se cambiato
		if (credentialsForm.getUsername() != null && !credentialsForm.getUsername().isBlank()
				&& !credentialsForm.getUsername().equals(credentials.getUsername())) {
			credentials.setUsername(credentialsForm.getUsername());
		}
		// Aggiorna password solo se fornita
		if (credentialsForm.getPassword() != null && !credentialsForm.getPassword().isBlank()) {
			credentials.setPassword(credentialsForm.getPassword());
		}
		userService.saveUser(currentUser);
		credentialsService.updateCredentials(credentials);
		return "redirect:/profile";
	}

	@GetMapping("/profile")
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
		model.addAttribute("recipeCount", userRecipes.size());
		model.addAttribute("reviewCount", userReviews.size());
		return "userProfile";
	}
}
