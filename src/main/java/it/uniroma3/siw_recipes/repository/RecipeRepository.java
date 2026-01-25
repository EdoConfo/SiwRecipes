package it.uniroma3.siw_recipes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw_recipes.model.Recipe;
import it.uniroma3.siw_recipes.model.User;

/**
 * Repository per le Ricette.
 * Qui definiamo query più complesse basate sui nomi dei metodi:
 * 
 * 1. findByAuthor(User author):
 *    Trova tutte le ricette scritte da un certo utente.
 *    SQL equiv: SELECT * FROM recipe WHERE author_id = ?
 * 
 * 2. findByTitleContainingIgnoreCase(String title):
 *    Cerca ricette il cui titolo contiene la stringa (senza distinguere maiuscole/minuscole).
 *    SQL equiv: SELECT * FROM recipe WHERE LOWER(title) LIKE LOWER('%title%')
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    /* Trova tutte le ricette scritte da un certo autore */
    List<Recipe> findByAuthor(User author);

    /* Cerca ricette il cui titolo contiene la stringa passata (case insensitive) */
    List<Recipe> findByTitleContainingIgnoreCase(String title);

    /* Trova le ultime 6 ricette create */
    List<Recipe> findTop6ByOrderByCreationDateDesc();

    /* Trova ricette che hanno nome file locale ma mancano di dati binari (per migrazione) */
    List<Recipe> findByImageDataIsNullAndImageIsNotNull();
}
