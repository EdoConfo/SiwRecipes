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

    @Transactional(readOnly = true)
    public Recipe getRecipeByImageFilename(String filename) {
        return this.recipeRepository.findByImage(filename);
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
     * Restituisce la lista di tutte le ricette presenti nel sistema (Summary Projection).
     * @return Lista di ricette ottimizzata.
     */
    @Transactional(readOnly = true)
    public List<it.uniroma3.siw_recipes.model.RecipeSummary> getAllRecipesSummary() {
        return this.recipeRepository.findAllBy();
    }
    
    /**
     * Restituisce la lista di tutte le ricette presenti nel sistema (Entity complete).
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
     * Cerca ricette che contengono una certa parola nel titolo (Entity).
     * @param title La parola chiave da cercare.
     * @return Lista di ricette corrispondenti.
     */
    @Transactional(readOnly = true)
    public List<Recipe> findByTitle(String title) {
        return this.recipeRepository.findByTitleContainingIgnoreCase(title);
    }

    /**
     * Cerca ricette che contengono una certa parola nel titolo (Summary).
     * @param title La parola chiave da cercare.
     * @return Lista di ricette (Summary) corrispondenti.
     */
    @Transactional(readOnly = true)
    public List<it.uniroma3.siw_recipes.model.RecipeSummary> findSummaryByTitle(String title) {
        return this.recipeRepository.findAllByTitleContainingIgnoreCase(title);
    }

    public List<it.uniroma3.siw_recipes.model.RecipeSummary> getLastRecipes() {
        return this.recipeRepository.findTop6ByOrderByCreationDateDesc();
    }

    //Count di tutte le ricette (countAllRecipes)
    @Transactional(readOnly = true)
    public long countAllRecipes() {
        return this.recipeRepository.count();
    }

    //Count di ricette per tipo (countRecipesByCategory)
    @Transactional(readOnly = true)
    public long countRecipesByCategory(String category) {
        return this.recipeRepository.countByCategory(category);
    }

    //Get delle ricette in base alla difficoltà (getRecipesByDifficulty(2))
    @Transactional(readOnly = true)
    public List<Recipe> getRecipesByDifficulty(int difficulty) {
        return this.recipeRepository.findByDifficulty(difficulty);
    }

    //findSummaryByAuthorId
    @Transactional(readOnly = true)
    public List<Recipe> findSummaryByAuthorId(Long authorId) {
        it.uniroma3.siw_recipes.model.User author = new it.uniroma3.siw_recipes.model.User();
        author.setId(authorId);
        return this.recipeRepository.findByAuthor(author);
    }

    //findByCategory
    @Transactional(readOnly = true)
    public List<Recipe> findByCategory(String category) {
        return this.recipeRepository.findByCategory(category);
    }

    //findByCreationDate (in recipe.java è un LocalDateTime, quindi bisogna fare un filtro manuale)
    @Transactional(readOnly = true)
    public List<Recipe> findByCreationDate(String date) {
        List<Recipe> recipes = new ArrayList<>();
        for (Recipe recipe : this.getAllRecipes()) {
            String recipeDate = recipe.getCreationDate().toLocalDate().toString();
            if (recipeDate.equals(date)) {
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    //findByPrepTime
    @Transactional(readOnly = true)
    public List<Recipe> findByPrepTime(int prepTime) {
        List<Recipe> recipes = new ArrayList<>();
        for (Recipe recipe : this.getAllRecipes()) {
            if (recipe.getPrepTime() == prepTime) {
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    //getRecipesByAverageRating(String rating) (la media è string non è salvata nel DB, quindi bisogna fare un filtro manuale)
    @Transactional(readOnly = true)
    public List<Recipe> getRecipesByAverageRating(String rating) {
        List<Recipe> recipes = new ArrayList<>();
        for (Recipe recipe : this.getAllRecipes()) {
            double avg = 0.0;
            if (recipe.getReviews() != null && !recipe.getReviews().isEmpty()) {
                double sum = 0.0;
                for (it.uniroma3.siw_recipes.model.Review review : recipe.getReviews()) {
                    sum += review.getRating();
                }
                avg = sum / recipe.getReviews().size();
            }
            String avgStr = String.format("%.1f", avg);
            if (avgStr.equals(rating)) {
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    //getRecipesSummaryOrderedByRating
    @Transactional(readOnly = true)
    public List<it.uniroma3.siw_recipes.model.RecipeSummary> getRecipesSummaryOrderedByRating() {
        List<it.uniroma3.siw_recipes.model.RecipeSummary> summaries = this.recipeRepository.findAllBy();
        summaries.sort((r1, r2) -> {
            double avg1 = 0.0;
            double avg2 = 0.0;
            if (r1.getReviews() != null && !r1.getReviews().isEmpty()) {
                double sum1 = 0.0;
                for (it.uniroma3.siw_recipes.model.Review review : r1.getReviews()) {
                    sum1 += review.getRating();
                }
                avg1 = sum1 / r1.getReviews().size();
            }
            if (r2.getReviews() != null && !r2.getReviews().isEmpty()) {
                double sum2 = 0.0;
                for (it.uniroma3.siw_recipes.model.Review review : r2.getReviews()) {
                    sum2 += review.getRating();
                }
                avg2 = sum2 / r2.getReviews().size();
            }
            return Double.compare(avg2, avg1);
        });
        return summaries;
    }

    //getRecipesSummaryOrderedByPrepTime
    @Transactional(readOnly = true)
    public List<it.uniroma3.siw_recipes.model.RecipeSummary> getRecipesSummaryOrderedByPrepTime() {
        List<it.uniroma3.siw_recipes.model.RecipeSummary> summaries = this.recipeRepository.findAllBy();
        summaries.sort((r1, r2) -> Integer.compare(r1.getPrepTime(), r2.getPrepTime()));
        return summaries;
    }

    //getRecipesSummaryOrderedByCreationDate
    @Transactional(readOnly = true)
    public List<it.uniroma3.siw_recipes.model.RecipeSummary> getRecipesSummaryOrderedByCreationDate() {
        List<it.uniroma3.siw_recipes.model.RecipeSummary> summaries = this.recipeRepository.findAllBy();
        summaries.sort((r1, r2) -> r2.getCreationDate().compareTo(r1.getCreationDate()));
        return summaries;
    }

    //getRecipesSummaryOrderedByDifficulty
    @Transactional(readOnly = true)
    public List<it.uniroma3.siw_recipes.model.RecipeSummary> getRecipesSummaryOrderedByDifficulty() {
        List<it.uniroma3.siw_recipes.model.RecipeSummary> summaries = this.recipeRepository.findAllBy();
        summaries.sort((r1, r2) -> Integer.compare(r1.getDifficulty(), r2.getDifficulty()));
        return summaries;
    }

    //getRecipesSummaryOrderedByCategory
    @Transactional(readOnly = true)
    public List<it.uniroma3.siw_recipes.model.RecipeSummary> getRecipesSummaryOrderedByCategory() {
        List<it.uniroma3.siw_recipes.model.RecipeSummary> summaries = this.recipeRepository.findAllBy();
        summaries.sort((r1, r2) -> r1.getCategory().compareToIgnoreCase(r2.getCategory()));
        return summaries;
    }

    //getRecipesSummaryOrderedByTitle
    @Transactional(readOnly = true)
    public List<it.uniroma3.siw_recipes.model.RecipeSummary> getRecipesSummaryOrderedByTitle() {
        List<it.uniroma3.siw_recipes.model.RecipeSummary> summaries = this.recipeRepository.findAllBy();
        summaries.sort((r1, r2) -> r1.getTitle().compareToIgnoreCase(r2.getTitle()));
        return summaries;
    }

    //findSummaryByAuthorNameOrTitle (usa solo RecipesSummary)
    @Transactional(readOnly = true)
    public List<it.uniroma3.siw_recipes.model.RecipeSummary> findSummaryByAuthorNameOrTitle(String keyword) {
        List<it.uniroma3.siw_recipes.model.RecipeSummary> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        for (it.uniroma3.siw_recipes.model.RecipeSummary summary : this.recipeRepository.findAllBy()) {
            String authorName = summary.getAuthor().getName().toLowerCase();
            String title = summary.getTitle().toLowerCase();
            if (authorName.contains(lowerKeyword) || title.contains(lowerKeyword)) {
                result.add(summary);
            }
        }
        return result;
    }

}
