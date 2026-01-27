package it.uniroma3.siw_recipes.service;

import java.awt.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw_recipes.model.Credentials;
import it.uniroma3.siw_recipes.repository.CredentialsRepository;

/**
 * CredentialsService gestisce la sicurezza e l'accesso.
 * 
 * Funzionalità principali:
 * - Verificare se uno username esiste già.
 * - Salvare nuove credenziali (magari cifrando la password).
 * - Recuperare le credenziali durante il login.
 * 
 * È il punto centrale per tutto ciò che riguarda l'autenticazione.
 */
@Service
public class CredentialsService {

    @Autowired
    protected CredentialsRepository credentialsRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    /**
     * Recupera le credenziali dato uno username.
     * Utile in fase di login per verificare se l'utente esiste.
     * @param username Lo username da cercare.
     * @return Le credenziali trovate, o null se non esistono.
     */
    @Transactional(readOnly = true)
    public Credentials getCredentials(String username) {
        Optional<Credentials> result = this.credentialsRepository.findByUsername(username);
        return result.orElse(null);
    }

    /**
     * Recupera le credenziali dato un ID.
     * @param id L'ID delle credenziali.
     * @return Le credenziali trovate, o null.
     */
    @Transactional(readOnly = true)
    public Credentials getCredentials(Long id) {
        Optional<Credentials> result = this.credentialsRepository.findById(id);
        return result.orElse(null);
    }
    
    @Transactional(readOnly = true)
    public Iterable<Credentials> getAllCredentials() {
        return this.credentialsRepository.findAll();
    }
    
    @Transactional
    public Credentials updateCredentials(Credentials credentials) {
        // Se la password non è già crittografata, la crittografa
        if (credentials.getPassword() != null && !credentials.getPassword().isBlank() && !credentials.getPassword().startsWith("$2a$")) {
            credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        }
        return this.credentialsRepository.save(credentials);
    }

    /**
     * Salva le credenziali nel database, cifrando la password e assegnando il ruolo di default.
     * @param credentials Le credenziali da salvare.
     * @return Le credenziali salvate.
     */
    @Transactional
    public Credentials saveCredentials(Credentials credentials) {
        credentials.setRole(Credentials.DEFAULT_ROLE);
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    }

    //getCredentialsByUserId
    @Transactional(readOnly = true)
    public Credentials getCredentialsByUserId(Long userId) {
        Optional<Credentials> result = this.credentialsRepository.findByUserId(userId);
        return result.orElse(null);
    }

    //getUserById
    @Transactional(readOnly = true)
    public it.uniroma3.siw_recipes.model.User getUserById(Long userId) {
        Credentials credentials = this.getCredentialsByUserId(userId);
        if (credentials != null) {
            return credentials.getUser();
        }
        return null;
    }

    //countAllUsers
    @Transactional(readOnly = true)
    public Long countAllUsers() {
        return this.credentialsRepository.count();
    }

    //findAllUsers
    @Transactional(readOnly = true)
    public Iterable<it.uniroma3.siw_recipes.model.User> findAllUsers() {
        Iterable<Credentials> allCredentials = this.credentialsRepository.findAll();
        ArrayList<it.uniroma3.siw_recipes.model.User> allUsers = new ArrayList<>();
        for (Credentials cred : allCredentials) {
            allUsers.add(cred.getUser());
        }
        return allUsers;
    }
}