package it.uniroma3.siw_recipes.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw_recipes.model.User;

/**
 * UserRepository è l'interfaccia che gestisce l'accesso ai dati per l'entità User.
 * Estendendo CrudRepository, otteniamo "gratis" metodi come:
 * - save(User u) -> Salva nel DB.
 * - findById(Long id) -> Cerca per ID.
 * - findAll() -> Restituisce tutti gli utenti.
 * - delete(User u) -> Cancella dal DB.
 * 
 * Spring Data JPA implementa automaticamente questa interfaccia a runtime.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    // Qui puoi definire query personalizzate, ad esempio:
    // boolean existsByEmail(String email);
}
