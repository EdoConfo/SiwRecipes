package it.uniroma3.siw_recipes.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * La classe User rappresenta un Utente del sistema (es. Mario Rossi).
 * È un'entità JPA, il che significa che ogni istanza di questa classe corrisponde a una riga nella tabella "users" del database.
 * 
 * Esempio:
 * User u = new User("Mario", "Rossi", "mario@email.com");
 * 
 * Ruolo nel sistema:
 * - Contiene i dati anagrafici (nome, cognome, email).
 * - È collegato alle Credenziali (per il login).
 * - È l'autore di Ricette e Recensioni.
 * 
 * Annotazioni principali:
 * @Entity: Dice a Hibernate "Questa classe è una tabella".
 * @Table(name = "users"): Specifica il nome della tabella (usiamo "users" perché "user" è parola riservata SQL).
 */
@Entity
@Table(name = "users") // "user" è una parola riservata in SQL (specialmente in PostgreSQL), quindi rinominiamo la tabella in "users"
public class User {

    /* 
     * @Id: Indica che questo campo è la Chiave Primaria (Primary Key) della tabella.
     * @GeneratedValue: Indica che il valore dell'ID viene generato automaticamente dal database 
     * (solitamente tramite una sequenza).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /* Dati anagrafici dell'utente */
    private String name;
    private String surname;
    private String email;
    
    // Esempio di campo data (opzionale)
    private LocalDate dateOfBirth;

    /* 
     * RELAZIONI (Commentate finché non crei le altre classi)
     * 
     * Qui definiamo le relazioni con le altre entità.
     * 
     * @OneToOne: Relazione 1-a-1 con le Credenziali.
     * mappedBy = "user": Indica che la "padrona" della relazione è la classe Credentials (che avrà il campo 'user').
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Credentials credentials;

    /*
     * @OneToMany: Un utente può scrivere molte ricette.
     * mappedBy = "author": Indica che nella classe Recipe c'è un campo chiamato 'author' che gestisce la relazione.
     * fetch = FetchType.LAZY: Le ricette vengono caricate dal DB solo quando esplicitamente richieste (performance).
     */
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Recipe> recipes;

    /*
     * Un utente può scrivere molte recensioni.
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Review> reviews;

    /*
     * COSTRUTTORE VUOTO
     * È obbligatorio per JPA. Hibernate lo usa per istanziare l'oggetto quando legge dal database.
     */
    public User() {
    }

    /*
     * COSTRUTTORE CON PARAMETRI
     * Utile per creare nuovi oggetti User nel codice Java.
     */
    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    /* GETTER E SETTER */

    public java.util.List<Review> getReviews() {
        return this.reviews;
    }

    public void setReviews(java.util.List<Review> reviews) {
        this.reviews = reviews;
    }
    
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    /*
     * EQUALS E HASHCODE
     * Fondamentali per il corretto funzionamento di Hibernate, specialmente quando si usano Set o Liste.
     * Qui usiamo l'ID per l'uguaglianza: due utenti sono lo stesso utente se hanno lo stesso ID database.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
