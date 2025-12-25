package it.uniroma3.siw_recipes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw_recipes.model.Ingredient;
import it.uniroma3.siw_recipes.repository.IngredientRepository;

/**
 * IngredientService gestisce gli ingredienti delle ricette.
 * 
 * Anche se spesso gli ingredienti sono salvati insieme alla ricetta (Cascade),
 * questo service è utile per operazioni dirette, come:
 * - Aggiornare la quantità di un ingrediente specifico.
 * - Cancellare un ingrediente errato.
 */
@Service
public class IngredientService {

    /*
     * @Autowired: Inietta automaticamente un'istanza di IngredientRepository.
     * Questo ci permette di interagire con il database senza dover istanziare manualmente il repository.
     */
    @Autowired
    protected IngredientRepository ingredientRepository;

    /**
     * Salva un ingrediente nel database.
     * Se l'ingrediente ha già un ID, aggiorna i dati esistenti.
     * Se non ha un ID, ne crea uno nuovo.
     * 
     * @param ingredient L'oggetto Ingredient da salvare.
     * @return L'ingrediente salvato (con l'ID aggiornato se era nuovo).
     */
    @Transactional
    public Ingredient saveIngredient(Ingredient ingredient) {
        return this.ingredientRepository.save(ingredient);
    }

    /**
     * Recupera un ingrediente dal database dato il suo ID.
     * 
     * @param id L'ID dell'ingrediente da cercare.
     * @return L'ingrediente trovato, oppure null se non esiste nessun ingrediente con quell'ID.
     */
    @Transactional(readOnly = true)
    public Ingredient getIngredient(Long id) {
        Optional<Ingredient> result = this.ingredientRepository.findById(id);
        return result.orElse(null);
    }
}
