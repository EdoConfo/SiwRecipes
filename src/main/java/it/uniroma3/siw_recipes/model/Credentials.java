package it.uniroma3.siw_recipes.model;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

/**
 * La classe Credentials rappresenta le chiavi di accesso al sistema (Username e Password).
 * 
 * Perché separarla da User?
 * È una best practice di sicurezza e design:
 * - User: Chi sei (Nome, Cognome, Email).
 * - Credentials: Come accedi (Username, Password, Ruolo).
 * 
 * Esempio:
 * Credentials c = new Credentials("mario123", "passwordSegreta", "DEFAULT");
 * 
 * Ruoli:
 * - "DEFAULT": Utente normale registrato.
 * - "ADMIN": Amministratore con poteri speciali.
 */
@Entity
public class Credentials {

    public static final String DEFAULT_ROLE = "DEFAULT";
    public static final String ADMIN_ROLE = "ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*
     * Username univoco per l'accesso.
     * @Column(unique = true): Garantisce che non ci siano due utenti con lo stesso username nel DB.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /*
     * La password deve essere salvata cifrata (non in chiaro).
     */
    @Column(nullable = false)
    private String password;

    /*
     * Il ruolo dell'utente nel sistema (es. "DEFAULT", "ADMIN").
     * Questo campo viene usato da Spring Security per gestire le autorizzazioni.
     */
    @Column(nullable = false)
    private String role;
    
    @Column(nullable = false)
    private boolean enabled = true;

    /*
     * RELAZIONE CON USER
     * @OneToOne: Ogni credenziale appartiene a un solo utente, e viceversa.
     * cascade = CascadeType.ALL: Se salvo/cancello una Credential, l'operazione si propaga all'User associato.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    /* COSTRUTTORE VUOTO (Obbligatorio per JPA) */
    public Credentials() {
    }

    /* COSTRUTTORE CON PARAMETRI */
    public Credentials(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /* GETTER E SETTER */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /* EQUALS E HASHCODE */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credentials that = (Credentials) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
