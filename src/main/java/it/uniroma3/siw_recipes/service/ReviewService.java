package it.uniroma3.siw_recipes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw_recipes.model.Recipe;
import it.uniroma3.siw_recipes.model.Review;
import it.uniroma3.siw_recipes.repository.ReviewRepository;

/**
 * ReviewService gestisce le recensioni degli utenti.
 * 
 * Funzionalità:
 * - Salvare una nuova recensione (saveReview).
 * - Leggere una recensione esistente.
 * 
 * In futuro potrebbe includere logica per calcolare la media dei voti di una ricetta.
 */
@Service
public class ReviewService {

    /*
     * @Autowired: Inietta automaticamente un'istanza di ReviewRepository.
     */
    @Autowired
    protected ReviewRepository reviewRepository;

    /**
     * Salva una recensione nel database.
     * Questo metodo viene utilizzato sia per creare nuove recensioni che per aggiornare quelle esistenti.
     * 
     * @param review L'oggetto Review da salvare.
     * @return La recensione salvata.
     */
    @Transactional
    public Review saveReview(Review review) {
        return this.reviewRepository.save(review);
    }

    /**
     * Recupera una recensione dal database tramite il suo ID.
     * 
     * @param id L'ID della recensione da cercare.
     * @return La recensione trovata, oppure null se non esiste.
     */
    @Transactional(readOnly = true)
    public Review getReview(Long id) {
        Optional<Review> result = this.reviewRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Review> getReviewsByRecipe(Recipe recipe) {
        return this.reviewRepository.findByRecipe(recipe);
    }

    @Transactional
    public void deleteReview(Long id) {
        this.reviewRepository.deleteById(id);
    }

    /**
     * Restituisce una mappa id ricetta -> media recensioni.
     */
    public Map<Long, String> getAverageRatingsForAllRecipes() {
        Map<Long, String> averageRatings = new HashMap<>();
        List<Object[]> results = reviewRepository.findAverageRatingForAllRecipes();
        for (Object[] row : results) {
            Long recipeId = (Long) row[0];
            Double avg = (Double) row[1];
            averageRatings.put(recipeId, avg != null ? String.format("%.2f", avg) : "-");
        }
        return averageRatings;
    }

    public long countAllReviews() {
        return this.reviewRepository.count();
    }
}
