package it.uniroma3.siw_recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw_recipes.model.Credentials;
import it.uniroma3.siw_recipes.model.Recipe;
import it.uniroma3.siw_recipes.model.Review;
import it.uniroma3.siw_recipes.model.User;
import it.uniroma3.siw_recipes.service.CredentialsService;
import it.uniroma3.siw_recipes.service.RecipeService;
import it.uniroma3.siw_recipes.service.ReviewService;
import jakarta.validation.Valid;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private CredentialsService credentialsService;

	@PostMapping("/recipe/{recipeId}/review")
	public String addReview(@PathVariable("recipeId") Long recipeId, @Valid @ModelAttribute("newReview") Review review, BindingResult bindingResult, Model model) {
		Recipe recipe = this.recipeService.getRecipe(recipeId);
		if (recipe == null) return "redirect:/recipes";
        
		User currentUser = this.getCurrentUser();
		if (currentUser == null) {
			return "redirect:/login";
		}
        
		if (recipe.getAuthor() != null && recipe.getAuthor().getId().equals(currentUser.getId())) {
			return "redirect:/recipe/" + recipeId;
		}
        
		if (bindingResult.hasErrors()) {
			// Ricarichiamo le recensioni per la vista
			recipe.setReviews(this.reviewService.getReviewsByRecipe(recipe));
            
			model.addAttribute("recipe", recipe);
			model.addAttribute("currentUser", currentUser);
            
			// Re-populate flags to avoid template errors
			boolean isAuthor = false;
			boolean isAdmin = false;
            
			if (recipe.getAuthor() != null && recipe.getAuthor().equals(currentUser)) {
				isAuthor = true;
			}
            
			Credentials credentials = this.getCurrentCredentials();
			if (credentials != null && credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
				isAdmin = true;
			}
            
			model.addAttribute("isAuthor", isAuthor);
			model.addAttribute("isAdmin", isAdmin);
            
			return "recipe";
		}
        
		review.setAuthor(currentUser);
		review.setRecipe(recipe);
		this.reviewService.saveReview(review);
        
		return "redirect:/recipe/" + recipeId;
	}

	@GetMapping("/review/{id}/edit")
	public String editReview(@PathVariable("id") Long id, Model model) {
		Review review = this.reviewService.getReview(id);
		if (review != null) {
			User currentUser = this.getCurrentUser();
			if (currentUser != null && review.getAuthor().getId().equals(currentUser.getId())) {
				// Redirect alla pagina della ricetta con parametro per attivare la modifica inline
				return "redirect:/recipe/" + review.getRecipe().getId() + "?editingReviewId=" + id + "#review-" + id;
			}
		}
		return "redirect:/recipes";
	}

	@PostMapping("/review/{id}/update")
	public String updateReview(@PathVariable("id") Long id, @Valid @ModelAttribute("review") Review review, BindingResult bindingResult, Model model) {
		Review oldReview = this.reviewService.getReview(id);
		if (oldReview == null) return "redirect:/recipes";
        
		User currentUser = this.getCurrentUser();
		if (currentUser == null || !oldReview.getAuthor().getId().equals(currentUser.getId())) {
			return "redirect:/recipes";
		}

		if (bindingResult.hasErrors()) {
			// Ricarichiamo la pagina della ricetta mantenendo lo stato di modifica
			Recipe recipe = oldReview.getRecipe();
			recipe.setReviews(this.reviewService.getReviewsByRecipe(recipe));
            
			model.addAttribute("recipe", recipe);
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("editingReviewId", id);
            
			// Re-populate flags
			boolean isAuthor = false; // Author of recipe, not review
			if (recipe.getAuthor() != null && recipe.getAuthor().getId().equals(currentUser.getId())) {
				isAuthor = true;
			}
            
			boolean isAdmin = false;
			Credentials credentials = this.getCurrentCredentials();
			if (credentials != null && credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
				isAdmin = true;
			}
            
			model.addAttribute("isAuthor", isAuthor);
			model.addAttribute("isAdmin", isAdmin);
            
			return "recipe";
		}

		oldReview.setTitle(review.getTitle());
		oldReview.setText(review.getText());
		oldReview.setRating(review.getRating());
        
		this.reviewService.saveReview(oldReview);
		return "redirect:/recipe/" + oldReview.getRecipe().getId() + "#review-" + id;
	}

	@GetMapping("/review/{id}/delete")
	public String deleteReview(@PathVariable("id") Long id) {
		Review review = this.reviewService.getReview(id);
		if (review != null) {
			Credentials credentials = this.getCurrentCredentials();
			if (credentials != null && (credentials.getRole().equals(Credentials.ADMIN_ROLE) || 
				(credentials.getUser() != null && review.getAuthor().getId().equals(credentials.getUser().getId())))) {
				Long recipeId = review.getRecipe().getId();
				this.reviewService.deleteReview(id);
				return "redirect:/recipe/" + recipeId;
			}
		}
		return "redirect:/recipes";
	}

	private User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails userDetails) {
			Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
			if (credentials != null) {
				return credentials.getUser();
			}
		}
		return null;
	}

	private Credentials getCurrentCredentials() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails userDetails) {
			return credentialsService.getCredentials(userDetails.getUsername());
		}
		return null;
	}
}
