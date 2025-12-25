package it.uniroma3.siw_recipes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

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
    
    /* Trova tutte le recensioni associate a una specifica ricetta */
    List<Review> findByRecipe(Recipe recipe);
}
