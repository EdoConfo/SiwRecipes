package it.uniroma3.siw_recipes.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

/**
 * La classe Recipe è il cuore dell'applicazione: rappresenta una Ricetta di cucina.
 * 
 * Esempio:
 * Recipe r = new Recipe("Carbonara", "Pasta con uova e guanciale", 20, 2);
 * 
 * Cosa contiene:
 * - Dati descrittivi (Titolo, Descrizione, Procedimento).
 * - Metadati (Tempo di preparazione, Difficoltà).
 * - Relazioni:
 *   - Chi l'ha scritta? (User author)
 *   - Cosa serve? (List<Ingredient> ingredients)
 *   - Cosa ne pensano gli altri? (List<Review> reviews)
 */
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    /* Descrizione breve della ricetta, visibile nelle anteprime */
    @Column(length = 500)
    private String description;

    /* 
     * Il procedimento completo. 
     * @Column(length = 2000): Aumentiamo la lunghezza massima del campo testo (default 255) 
     * per permettere descrizioni lunghe.
     */
    @Column(length = 2000)
    private String procedure;

    /* Tempo di preparazione in minuti */
    private int prepTime;

    /* Livello di difficoltà (es. 1=Facile, 2=Medio, 3=Difficile) */
    private int difficulty;

    /* Categoria della ricetta (es. Primi, Secondi, Dolci) */
    @NotBlank
    private String category;

    /* Data di inserimento della ricetta */
    private LocalDateTime creationDate;

    /* Nome del file dell'immagine della ricetta */
    private String image;

    /*
     * RELAZIONI
     */

    /*
     * L'autore della ricetta.
     * @ManyToOne: Molte ricette possono essere scritte da un solo utente.
     */
    @ManyToOne
    private User author;

    /*
     * Gli ingredienti della ricetta.
     * @OneToMany: Una ricetta ha molti ingredienti.
     * cascade = CascadeType.ALL: Se cancello la ricetta, cancello anche i suoi ingredienti.
     * orphanRemoval = true: Se rimuovo un ingrediente dalla lista, viene cancellato dal DB.
     */
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients;

    /*
     * Le recensioni ricevute.
     */
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE)
    private List<Review> reviews;

    /* COSTRUTTORE VUOTO */
    public Recipe() {
        this.creationDate = LocalDateTime.now(); // Imposta la data corrente alla creazione
    }

    /* COSTRUTTORE CON PARAMETRI */
    public Recipe(String title, String description, int prepTime, int difficulty) {
        this();
        this.title = title;
        this.description = description;
        this.prepTime = prepTime;
        this.difficulty = difficulty;
    }

    /* GETTER E SETTER */
    
    // Metodi di accesso
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    /* EQUALS E HASHCODE */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
