package it.uniroma3.siw_recipes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw_recipes.model.Recipe;
import it.uniroma3.siw_recipes.repository.RecipeRepository;

/**
 * RecipeService gestisce il catalogo delle Ricette.
 * 
 * Cosa fa:
 * - Permette di salvare nuove ricette (saveRecipe).
 * - Recupera ricette per ID o tutte insieme (getRecipe, getAllRecipes).
 * - Esegue ricerche specifiche (es. "Trova ricette con 'Pasta' nel titolo").
 * 
 * Questo service viene usato dai Controller per mostrare le ricette nelle pagine HTML.
 */
@Service
public class RecipeService {

    @Autowired
    protected RecipeRepository recipeRepository;

    /**
     * Salva una nuova ricetta o ne aggiorna una esistente.
     * @param recipe La ricetta da salvare.
     * @return La ricetta salvata.
     */
    @Transactional
    public Recipe saveRecipe(Recipe recipe) {
        return this.recipeRepository.save(recipe);
    }

    /**
     * Recupera una ricetta dato il suo ID.
     * @param id L'ID della ricetta.
     * @return La ricetta trovata, o null.
     */
    @Transactional(readOnly = true)
    public Recipe getRecipe(Long id) {
        Optional<Recipe> result = this.recipeRepository.findById(id);
        if (result.isPresent()) {
            Recipe recipe = result.get();
            // Inizializza gli ingredienti per evitare LazyInitializationException
            if (recipe.getIngredients() != null) {
                recipe.getIngredients().size();
            }
            return recipe;
        }
        return null;
    }

    /**
     * Cancella una ricetta dato il suo ID.
     * @param id L'ID della ricetta da cancellare.
     */
    @Transactional
    public void deleteRecipe(Long id) {
        this.recipeRepository.deleteById(id);
    }

    /**
     * Recupera tutte le ricette scritte da un certo autore.
     * @param author L'autore delle ricette.
     * @return Una lista di ricette.
     */
    @Transactional(readOnly = true)
    public List<Recipe> getRecipesByAuthor(it.uniroma3.siw_recipes.model.User author) {
        return this.recipeRepository.findByAuthor(author);
    }

    /**
     * Restituisce la lista di tutte le ricette presenti nel sistema.
     * @return Lista di ricette.
     */
    @Transactional(readOnly = true)
    public List<Recipe> getAllRecipes() {
        List<Recipe> result = new ArrayList<>();
        Iterable<Recipe> iterable = this.recipeRepository.findAll();
        for(Recipe recipe : iterable)
            result.add(recipe);
        return result;
    }
    
    /**
     * Cerca ricette che contengono una certa parola nel titolo.
     * @param title La parola chiave da cercare.
     * @return Lista di ricette corrispondenti.
     */
    @Transactional(readOnly = true)
    public List<Recipe> findByTitle(String title) {
        return this.recipeRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Recipe> getLastRecipes() {
        return this.recipeRepository.findTop6ByOrderByCreationDateDesc();
    }
}
