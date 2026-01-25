package it.uniroma3.siw_recipes.model;

import java.time.LocalDateTime;

/**
 * Interfaccia di Proiezione (Projection) per la Ricetta.
 * Serve a recuperare dal Databse solo i dati "leggeri", escludendo il campo 'imageData' (BLOB/byte[]) 
 * che appesantisce incredibilmente le query di lista.
 */
public interface RecipeSummary {
    
    Long getId();
    String getTitle();
    String getDescription();
    int getPrepTime();
    int getDifficulty();
    String getCategory();
    String getImage(); // Solo il nome del file, non i byte!
    LocalDateTime getCreationDate();
    
    // Per ottenere l'autore (User è leggero, quindi possiamo includerlo)
    User getAuthor();
    
    // Per ingredienti e review dovremo fare attenzione, 
    // ma Spring Data JPA supporta le proiezioni annidate o il ritorno dell'entità collegata.
    // Se review/ingredienti sono caricati Lazy, qui va bene restituire le entità se non le tocchiamo troppo.
    // Ma per sicurezza, se serve solo il conteggio o altro, meglio gestire diversamente.
    // Per ora, concentriamoci sulla Home/List view che non mostra ingredienti/review in dettaglio.
}
