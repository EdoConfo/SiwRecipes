package it.uniroma3.siw_recipes.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw_recipes.model.Ingredient;

/**
 * Repository per gli Ingredienti.
 * Al momento non servono query personalizzate, bastano quelle standard (save, delete, findById).
 * 
 * Nota: Spesso gli ingredienti vengono gestiti "a cascata" dalla Ricetta (CascadeType.ALL),
 * ma avere un repository dedicato può essere utile per operazioni specifiche o statistiche.
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
