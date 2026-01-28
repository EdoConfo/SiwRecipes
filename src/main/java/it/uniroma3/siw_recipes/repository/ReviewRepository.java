package it.uniroma3.siw_recipes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw_recipes.model.Recipe;
import it.uniroma3.siw_recipes.model.Review;

/**
 * Repository per le Recensioni.
 * 
 * Metodo personalizzato:
 * - findByRecipe(Recipe recipe):
 *   Restituisce tutte le recensioni associate a una specifica ricetta.
 *   Utile per mostrare la lista dei commenti sotto la pagina di una ricetta.
 */
public interface ReviewRepository extends CrudRepository<Review, Long> {
    
    /* Trova tutte le recensioni associate a una specifica ricetta, escludendo quelle di utenti bannati */
    @Query("SELECT r FROM Review r WHERE r.recipe = :recipe AND r.author.credentials.enabled = true")
    List<Review> findByRecipe(@Param("recipe") Recipe recipe);

    /**
     * Restituisce la media delle recensioni per tutte le ricette (id -> media).
     */
    @Query("SELECT r.recipe.id, AVG(r.rating) FROM Review r WHERE r.author.credentials.enabled = true GROUP BY r.recipe.id")
    List<Object[]> findAverageRatingForAllRecipes();

    //findByRecipeIn(recipes)
    List<Review> findByRecipeIn(List<Recipe> recipes);

    //findAverageRatingByRecipeId(recipeId)
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.recipe.id = :recipeId AND r.author.credentials.enabled = true")
    Double findAverageRatingByRecipeId(@Param("recipeId") Long recipeId);
}
