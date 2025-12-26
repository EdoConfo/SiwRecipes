package it.uniroma3.siw_recipes.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

/**
 * La classe Ingredient rappresenta una riga nella lista della spesa di una ricetta.
 * 
 * Esempio:
 * - "200 grammi di Farina" -> name="Farina", quantity="200", unitOfMeasure="grammi"
 * - "3 Uova" -> name="Uova", quantity="3", unitOfMeasure=""
 * 
 * Relazione:
 * È strettamente legato a una Ricetta (@ManyToOne). Se cancelli la ricetta,
 * solitamente non ha senso mantenere i suoi ingredienti orfani nel DB.
 */
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /* Nome dell'ingrediente (es. "Farina 00", "Uova") */
    @Column(nullable = false)
    private String name;

    /* Quantità dell'ingrediente (es. "200", "1/2") */
    @Column(nullable = false)
    @NotBlank
    private String quantity;

    /* Unità di misura (es. "g", "ml", "cucchiai") */
    private String unitOfMeasure;

    /*
     * RELAZIONE CON RECIPE
     * @ManyToOne: Molti ingredienti sono associati a una singola ricetta.
     * Questa è la parte "proprietaria" della relazione (contiene la Foreign Key verso Recipe).
     */
    @ManyToOne
    private Recipe recipe;

    /* COSTRUTTORE VUOTO (Obbligatorio per JPA) */
    public Ingredient() {
    }

    /* COSTRUTTORE CON PARAMETRI */
    public Ingredient(String name, String quantity, String unitOfMeasure) {
        this.name = name;
        this.quantity = quantity;
        this.unitOfMeasure = unitOfMeasure;
    }

    /* GETTER E SETTER */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    /* EQUALS E HASHCODE */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
