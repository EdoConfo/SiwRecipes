package it.uniroma3.siw_recipes.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static it.uniroma3.siw_recipes.model.Credentials.ADMIN_ROLE;

/**
 * La classe AuthConfiguration definisce le politiche di sicurezza dell'applicazione.
 * Qui decidiamo:
 * - Quali pagine sono pubbliche e quali richiedono login.
 * - Come avviene il login (form login).
 * - Come avviene il logout.
 * - Come vengono criptate le password.
 * - Da dove vengono presi i dati degli utenti (DataSource).
 */
@Configuration
@EnableWebSecurity
public class AuthConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*
         * Configurazione dell'autenticazione JDBC.
         * Spring Security interrogherà il database per verificare username e password.
         * 
         * Query 1 (usersByUsernameQuery): Recupera username, password e stato attivo (enabled) dato l'username.
         * Query 2 (authoritiesByUsernameQuery): Recupera il ruolo (authority) dell'utente dato l'username.
         */
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .authoritiesByUsernameQuery("SELECT username, role FROM credentials WHERE username=?")
                .usersByUsernameQuery("SELECT username, password, enabled FROM credentials WHERE username=?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        /*
         * Usiamo BCrypt per criptare le password. 
         * È molto sicuro perché aggiunge un "salt" casuale ad ogni hash.
         */
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> auth
                    // .requestMatchers("/**").permitAll() // Scommenta per rendere tutto pubblico in fase di debug
                    .requestMatchers("/css/**", "/images/**", "favicon.ico").permitAll()
                    .requestMatchers("/", "/index", "/register", "/login").permitAll()
                    .requestMatchers(HttpMethod.GET, "/recipes", "/recipe/**").permitAll()
                    .requestMatchers("/admin/**").hasAnyAuthority(ADMIN_ROLE)
                    .anyRequest().authenticated()
                )
                .formLogin(form -> form
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/login?error=true")
                )
                .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .clearAuthentication(true)
                    .permitAll()
                );
                
        return httpSecurity.build();
    }
}
