package it.uniroma3.siw_recipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Questa è la classe principale (Main) dell'applicazione Spring Boot.
 * Funge da punto di ingresso (Entry Point), simile alla chiave di accensione di un'auto.
 * 
 * Quando avvii questa classe:
 * 1. Spring Boot si inizializza.
 * 2. Viene scansionato tutto il progetto alla ricerca di Componenti, Controller, Service e Repository.
 * 3. Viene avviato il server web integrato (Tomcat) sulla porta 8080.
 * 
 * L'annotazione @SpringBootApplication è una scorciatoia che include:
 * - @Configuration: Indica che questa classe può definire Bean.
 * - @EnableAutoConfiguration: Dice a Spring di configurarsi automaticamente in base alle dipendenze (es. se vede un DB, configura la connessione).
 * - @ComponentScan: Cerca altri componenti nel package corrente e nei sotto-package.
 */
@SpringBootApplication
public class SiwRecipesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiwRecipesApplication.class, args);
	}

}
