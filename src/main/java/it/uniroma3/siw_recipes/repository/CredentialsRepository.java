package it.uniroma3.siw_recipes.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw_recipes.model.Credentials;

/**
 * Repository per gestire le Credenziali.
 * Oltre ai metodi CRUD standard, abbiamo definito un metodo personalizzato:
 * - findByUsername(String username)
 * 
 * Come funziona?
 * Spring Data JPA analizza il nome del metodo ("findByUsername") e genera automaticamente
 * la query SQL corrispondente: "SELECT * FROM credentials WHERE username = ?".
 */
public interface CredentialsRepository extends CrudRepository<Credentials, Long> {
    
    /*
     * Metodo per trovare le credenziali dato uno username.
     * Restituisce un Optional perché lo username potrebbe non esistere.
     */
    Optional<Credentials> findByUsername(String username);

    Optional<Credentials> findByUserId(Long userId);
}
