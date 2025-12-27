# Progetto assegnato dal docente

Appello Gennaio febbraio AA 2024-2025

# SiwRecipes

- Il sistema ha l’obiettivo di gestire e rendere fruibili ricette di cucina tramite una piattaforma web, consentendo: la consultazione pubblica delle ricette; la creazione e recensione di ricette da parte di utenti registrati; la moderazione dei contenuti da parte di amministratori.
- **Tipologie di utenti e permessi**
    - **Utenti occasionali (non registrati)** possono: visualizzare l’elenco delle ricette; consultare il dettaglio di una ricetta (ingredienti, preparazione, autore); leggere le recensioni associate alle ricette. Gli utenti occasionali non possono: inserire ricette; scrivere recensioni; modificare contenuti.
    - **Utenti registrati**: previa autenticazione, possono: inserire nuove ricette; modificare o cancellare le proprie ricette; scrivere recensioni e valutazioni sulle ricette; modificare o cancellare le proprie recensioni; consultare tutte le ricette e recensioni.
    - **Amministratori**: hanno tutti i privilegi degli utenti registrati e, inoltre, possono: cancellare qualsiasi ricetta; cancellare recensioni inappropriate; bannare utenti registrati (disabilitandone l’accesso); visualizzare l’elenco degli utenti e il loro stato (attivo/bannato).
- **Funzionalità principali**
    - **Gestione delle ricette**. Ogni ricetta è caratterizzata da: titolo; descrizione breve; elenco degli ingredienti (quantità + unità di misura); procedimento (testo libero); tempo di preparazione; difficoltà; categoria (es. primi piatti, dolci, vegetariane); autore; data di inserimento. Funzioni disponibili: creazione ricetta (utenti registrati); modifica/cancellazione ricetta (autore o amministratore); visualizzazione pubblica.
    - **Gestione delle recensioni**. Ogni recensione include: testo della recensione; valutazione numerica (es. 1–5 stelle); autore; data di inserimento. Funzioni disponibili: inserimento recensione (utenti registrati); modifica/cancellazione recensione (autore); cancellazione recensione (amministratore).
    - **Gestione utenti**. Il sistema prevede: registrazione utenti; autenticazione (login/logout); gestione del profilo utente; stato dell’utente (attivo / bannato). Gli utenti bannati non possono accedere alle funzionalità riservate.
- Le specifiche possono essere completate dal candidato, laddove ritenuto necessario.

- Progettare il sistema, definendone casi d’uso, modello del dominio (con indicazioni utili alla progettazione dello strato di persistenza)
- Implementare almeno 6 casi d’uso:
    - Almeno due che abbiamo come attore gli amministratori (almeno uno di inserimento dati ed almeno uno di aggiornamento dati)
    - Almeno due che abbiamo come attore l'utente registrato (almeno uno di inserimento)
    - Almeno due che abbiano come attore l’utente generico
    - N.B. Autenticazione e registrazione dell'utente non sono considerati casi d'uso
- Implementare il sistema con Spring Boot. Saranno oggetto di valutazione anche la qualità del codice e la qualità dell’interfaccia HTML+CSS (è possibile usare librerie CSS, come, ad esempio, Boostrap).
- Il deploy su una piattaforma cloud (ad esempio, AWS, Azure, o Heroku) e l’autenticazione tramite Oauth saranno considerati un plus

# Modalità di Consegna

- Inviare un messaggio di posta elettronica all'indirizzo [siw.roma3@gmail.com](mailto:siw.roma3@gmail.com) entro le ore 18:00 del giorno precedente alla data dell'orale
- Nel corpo del messaggio riportare:
    - indirizzo github (o altra piattaforma git) del progetto;
    - eventuali malfunzionamenti noti, ma non risolti;
    - considerazioni generali sull’esperienza.
- L'oggetto del messaggio deve iniziare con la stringa [Gennaio/Febbraio 2026] seguita da cognome e matricola dello studente. Esempio: lo studente Francesco De Rossi con matricola 123456 che si prenota per l'appello di giugno l’oggetto del messaggio deve essere:
[Gennaio/Febbraio 2026] De Rossi 123456