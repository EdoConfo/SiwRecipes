package it.uniroma3.siw_recipes.configuration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

/**
 * Questa classe gestisce le operazioni da eseguire alla chiusura dell'applicazione.
 * In particolare, esegue un dump dei dati del database su file SQL per preservarli
 * tra un riavvio e l'altro, dato che il database viene ricreato ad ogni avvio (ddl-auto=create).
 */
@Configuration
public class ShutdownConfig {

    @Value("${spring.datasource.password}")
    private String databasePassword;

    @EventListener(ContextClosedEvent.class)
    public void onContextClosed() {
        
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder processBuilder;

        // Seleziona lo script corretto in base al sistema operativo
        if (os.contains("win")) {
            System.out.println("\nRilevato Windows, eseguo lo script PowerShell per il dump dei dati...");
            processBuilder = new ProcessBuilder("powershell.exe", "-ExecutionPolicy", "Bypass", "-File", "shutdown-script.ps1");
        } else {
            System.out.println("\nRilevato macOS/Linux, eseguo lo script Shell per il dump dei dati...");
            processBuilder = new ProcessBuilder("bash", "shutdown-script.sh");
        }

        try {
            // Aggiungo la password del DB all'ambiente del processo per pg_dump
            // Questo evita che pg_dump chieda la password in modo interattivo
            if (databasePassword != null && !databasePassword.isEmpty()) {
                processBuilder.environment().put("PGPASSWORD", databasePassword);
            }

            // Eredita l'IO del processo padre per vedere l'output nella console di Spring Boot
            processBuilder.inheritIO();

            Process process = processBuilder.start();
            
            // Aspetta un massimo di 30 secondi per il completamento del comando
            boolean finished = process.waitFor(30, TimeUnit.SECONDS);

            if (finished) {
                int exitValue = process.exitValue();
                if (exitValue == 0) {
                    System.out.println("Dump dei dati completato con successo.\n");
                } else {
                    System.err.println("Il comando di dump è terminato con errori (codice: " + exitValue + ").\n");
                }
            } else {
                System.out.println("Il comando di dump non è terminato entro 30 secondi, forzo l'interruzione.\n");
                process.destroyForcibly();
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Errore durante l'esecuzione del comando di shutdown: " + e.getMessage() + "\n");
            Thread.currentThread().interrupt();
        }
    }
}
