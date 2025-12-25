package it.uniroma3.siw_recipes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw_recipes.model.User;
import it.uniroma3.siw_recipes.repository.UserRepository;

/**
 * UserService è il "cervello" che gestisce gli Utenti.
 * 
 * Ruolo:
 * - Riceve richieste dai Controller (es. "Registra questo nuovo utente").
 * - Esegue controlli di validità (es. "L'email è valida?").
 * - Chiama il Repository per salvare/leggere i dati nel DB.
 * 
 * Annotazioni:
 * @Service: Dice a Spring "Questa classe contiene logica di business".
 * @Transactional: Assicura che ogni metodo venga eseguito in una transazione.
 *                 Se qualcosa va storto, viene fatto il rollback (annullamento modifiche).
 */
@Service
public class UserService {

    /*
     * @Autowired: Inietta automaticamente un'istanza di UserRepository.
     * Non dobbiamo istanziarlo noi con 'new', ci pensa Spring.
     */
    @Autowired
    protected UserRepository userRepository;

    /**
     * Recupera un utente dal database dato il suo ID.
     * @param id L'ID dell'utente da cercare.
     * @return L'utente trovato, o null se non esiste.
     */
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        Optional<User> result = this.userRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * Salva un utente nel database.
     * Se l'utente ha già un ID, aggiorna i dati esistenti.
     * Se non ha un ID, ne crea uno nuovo.
     * @param user L'oggetto User da salvare.
     * @return L'utente salvato (con l'ID aggiornato se era nuovo).
     */
    @Transactional
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    /**
     * Recupera tutti gli utenti presenti nel database.
     * @return Una lista di tutti gli utenti.
     */
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        Iterable<User> iterable = this.userRepository.findAll();
        // Convertiamo l'Iterable restituito da findAll() in una List
        for(User user : iterable)
            result.add(user);
        return result;
    }
}
