package it.uniroma3.siw_recipes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
