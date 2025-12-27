package it.uniroma3.siw_recipes.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * La classe Review rappresenta l'opinione di un utente su una ricetta.
 * È il feedback della community.
 * 
 * Esempio:
 * Review r = new Review("Buonissima!", "Ho provato a farla ed è venuta perfetta.", 5);
 * 
 * Componenti:
 * - Titolo e Testo (il contenuto).
 * - Rating (voto numerico, es. stelle da 1 a 5).
 * - Autore (chi l'ha scritta).
 * - Ricetta (cosa è stato recensito).
 */
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /* Titolo della recensione */
    @NotBlank
    private String title;

    /* Testo della recensione */
    @Column(length = 1000)
    @NotBlank
    private String text;

    /* Valutazione numerica (es. da 1 a 5 stelle) */
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;

    private LocalDateTime creationDate;

    /*
     * RELAZIONI
     */

    /*
     * L'autore della recensione.
     * @ManyToOne: Un utente può scrivere molte recensioni.
     */
    @ManyToOne
    private User author;

    /*
     * La ricetta recensita.
     * @ManyToOne: Una ricetta può avere molte recensioni.
     */
    @ManyToOne
    private Recipe recipe;

    /* COSTRUTTORE VUOTO */
    public Review() {
        this.creationDate = LocalDateTime.now();
    }

    /* COSTRUTTORE CON PARAMETRI */
    public Review(String title, String text, Integer rating) {
        this();
        this.title = title;
        this.text = text;
        this.rating = rating;
    }

    /* GETTER E SETTER */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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
        Review review = (Review) o;
        return Objects.equals(id, review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
