package it.uniroma3.siw_recipes.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw_recipes.model.Credentials;
import it.uniroma3.siw_recipes.model.Recipe;
import it.uniroma3.siw_recipes.service.CredentialsService;
import it.uniroma3.siw_recipes.service.RecipeService;
import jakarta.validation.Valid;

/**
 * RecipeController è il componente che gestisce le pagine web relative alle Ricette.
 * 
 * Ruolo nel sistema (Analogià del Ristorante):
 * Se il Service è lo "Chef" che cucina (logica di business) e il Repository è il "Magazzino" (database),
 * il Controller è il "Cameriere". Prende l'ordine dal cliente (Browser), lo porta in cucina,
 * e torna con il piatto pronto (Pagina HTML con i dati).
 * 
 * Esempio di flusso:
 * 1. L'utente clicca su "Tutte le ricette" -> Richiesta GET /recipes
 * 2. Questo controller intercetta la richiesta.
 * 3. Chiama recipeService.getAllRecipes() per farsi dare la lista.
 * 4. Mette la lista nel "Model" (il vassoio del cameriere).
 * 5. Restituisce la vista "recipes.html" che userà quei dati per disegnare la pagina.
 * 
 * Annotazioni:
 * @Controller: Registra questa classe come gestore di richieste web.
 */
@Controller
public class RecipeController {

    /*
     * Iniettiamo il Service per poter accedere alla logica delle ricette.
     */
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private CredentialsService credentialsService;

    @GetMapping("/recipes/new")
    public String formNewRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "formNewRecipe";
    }

    @PostMapping("/recipes")
    public String newRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult, 
            @RequestParam("file") MultipartFile file, Model model) {
        if (bindingResult.hasErrors()) {
            return "formNewRecipe";
        } else {
            UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
            recipe.setAuthor(credentials.getUser());
            
            if (!file.isEmpty()) {
                try {
                    String surname = credentials.getUser().getSurname();
                    String name = credentials.getUser().getName();
                    String recipeName = recipe.getTitle().toLowerCase().replaceAll("\\s+", "-");
                    
                    String baseFileName = surname + "-" + name + "-" + recipeName;
                    String uploadDirSrc = "src/main/resources/static/images/uploads/";
                    String uploadDirTarget = "target/classes/static/images/uploads/";
                    
                    Path uploadPathSrc = Paths.get(uploadDirSrc);
                    Path uploadPathTarget = Paths.get(uploadDirTarget);
                    
                    if (!Files.exists(uploadPathSrc)) Files.createDirectories(uploadPathSrc);
                    if (!Files.exists(uploadPathTarget)) Files.createDirectories(uploadPathTarget);

                    String finalFileName;
                    long timestamp = System.currentTimeMillis();

                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(file.getInputStream());
                    } catch (Exception e) {
                        // Ignora eccezioni di lettura, gestite dal fallback
                    }

                    if (image != null) {
                        // Conversione in JPG
                        finalFileName = baseFileName + "-" + timestamp + ".jpg";
                        Path filePathSrc = uploadPathSrc.resolve(finalFileName);
                        Path filePathTarget = uploadPathTarget.resolve(finalFileName);

                        BufferedImage newBufferedImage = new BufferedImage(image.getWidth(),
                                image.getHeight(), BufferedImage.TYPE_INT_RGB);
                        newBufferedImage.createGraphics().drawImage(image, 0, 0, java.awt.Color.WHITE, null);
                        
                        ImageIO.write(newBufferedImage, "jpg", filePathSrc.toFile());
                        ImageIO.write(newBufferedImage, "jpg", filePathTarget.toFile());
                        
                        recipe.setImage(finalFileName);
                    } else {
                        // Fallback: salva il file originale
                        String originalExt = "unknown";
                        String originalName = file.getOriginalFilename();
                        if (originalName != null && originalName.lastIndexOf(".") != -1) {
                            originalExt = originalName.substring(originalName.lastIndexOf(".") + 1);
                        }

                        finalFileName = baseFileName + "-" + timestamp + "." + originalExt;
                        Path filePathSrc = uploadPathSrc.resolve(finalFileName);
                        Path filePathTarget = uploadPathTarget.resolve(finalFileName);
                        
                        Files.copy(file.getInputStream(), filePathSrc);
                        Files.copy(filePathSrc, filePathTarget);
                        
                        recipe.setImage(finalFileName);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            this.recipeService.saveRecipe(recipe);
            return "redirect:/recipe/" + recipe.getId();
        }
    }

    /**
     * Gestisce la richiesta per visualizzare l'elenco di tutte le ricette.
     * URL: /recipes
     * 
     * @param model Il "contenitore" dove mettiamo i dati da inviare alla pagina HTML.
     * @return Il nome del file HTML da visualizzare (senza .html).
     */
    @GetMapping("/recipes")
    public String getRecipes(Model model) {
        // 1. Chiediamo al service tutte le ricette
        // 2. Le aggiungiamo al modello con il nome "recipes"
        // Nella pagina HTML potremo usare th:each="recipe : ${recipes}"
        model.addAttribute("recipes", this.recipeService.getAllRecipes());
        
        // 3. Restituiamo il nome della vista (src/main/resources/templates/recipes.html)
        return "recipes";
    }

    /**
     * Gestisce la richiesta per visualizzare i dettagli di una singola ricetta.
     * URL: /recipe/{id} (es. /recipe/1, /recipe/50)
     * 
     * @param id L'ID della ricetta preso dall'URL (grazie a @PathVariable).
     * @param model Il modello per passare i dati alla vista.
     * @return Il nome della vista di dettaglio o la lista se non trovata.
     */
    @GetMapping("/recipe/{id}")
    public String getRecipe(@PathVariable("id") Long id, Model model) {
        // Cerchiamo la ricetta per ID
        Recipe recipe = this.recipeService.getRecipe(id);

        if (recipe != null) {
            // Se esiste, la mettiamo nel modello
            model.addAttribute("recipe", recipe);
            
            // Aggiungiamo l'utente corrente se loggato
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
                model.addAttribute("currentUser", credentials.getUser());
            }
            
            return "recipe"; // Mostra recipe.html
        } else {
            // Se non esiste (es. ID sbagliato), torniamo alla lista
            // (In futuro potremmo mostrare una pagina di errore 404)
            return "redirect:/recipes";
        }
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable("id") Long id) {
        Recipe recipe = this.recipeService.getRecipe(id);
        if (recipe != null) {
            UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
            if (recipe.getAuthor().getId().equals(credentials.getUser().getId())) {
                // Elimina l'immagine associata se esiste
                if (recipe.getImage() != null) {
                    String uploadDirSrc = "src/main/resources/static/images/uploads/";
                    String uploadDirTarget = "target/classes/static/images/uploads/";
                    
                    try {
                        Files.deleteIfExists(Paths.get(uploadDirSrc + recipe.getImage()));
                        Files.deleteIfExists(Paths.get(uploadDirTarget + recipe.getImage()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                this.recipeService.deleteRecipe(id);
            }
        }
        return "redirect:/recipes";
    }

    @GetMapping("/recipe/{id}/edit")
    public String editRecipe(@PathVariable("id") Long id, Model model) {
        Recipe recipe = this.recipeService.getRecipe(id);
        if (recipe != null) {
            UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
            if (recipe.getAuthor().getId().equals(credentials.getUser().getId())) {
                model.addAttribute("recipe", recipe);
                return "formEditRecipe";
            }
        }
        return "redirect:/recipes";
    }

    @PostMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable("id") Long id, @Valid @ModelAttribute("recipe") Recipe recipe, 
            BindingResult bindingResult, @RequestParam("file") MultipartFile file, Model model) {
        if (bindingResult.hasErrors()) {
            return "formEditRecipe";
        } else {
            Recipe oldRecipe = this.recipeService.getRecipe(id);
            if (oldRecipe == null) {
                return "redirect:/recipes";
            }
            
            // Aggiorniamo i campi
            oldRecipe.setTitle(recipe.getTitle());
            oldRecipe.setDescription(recipe.getDescription());
            oldRecipe.setPrepTime(recipe.getPrepTime());
            oldRecipe.setDifficulty(recipe.getDifficulty());
            oldRecipe.setProcedure(recipe.getProcedure());
            
            if (!file.isEmpty()) {
                // Elimina la vecchia immagine se esiste
                if (oldRecipe.getImage() != null) {
                    String uploadDirSrc = "src/main/resources/static/images/uploads/";
                    String uploadDirTarget = "target/classes/static/images/uploads/";
                    try {
                        Files.deleteIfExists(Paths.get(uploadDirSrc + oldRecipe.getImage()));
                        Files.deleteIfExists(Paths.get(uploadDirTarget + oldRecipe.getImage()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    String surname = oldRecipe.getAuthor().getSurname();
                    String name = oldRecipe.getAuthor().getName();
                    String recipeName = recipe.getTitle().toLowerCase().replaceAll("\\s+", "-");
                    
                    String baseFileName = surname + "-" + name + "-" + recipeName;
                    String uploadDirSrc = "src/main/resources/static/images/uploads/";
                    String uploadDirTarget = "target/classes/static/images/uploads/";
                    
                    Path uploadPathSrc = Paths.get(uploadDirSrc);
                    Path uploadPathTarget = Paths.get(uploadDirTarget);
                    
                    if (!Files.exists(uploadPathSrc)) Files.createDirectories(uploadPathSrc);
                    if (!Files.exists(uploadPathTarget)) Files.createDirectories(uploadPathTarget);

                    String finalFileName;
                    long timestamp = System.currentTimeMillis();

                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(file.getInputStream());
                    } catch (Exception e) {
                        // Ignora eccezioni di lettura
                    }

                    if (image != null) {
                        finalFileName = baseFileName + "-" + timestamp + ".jpg";
                        Path filePathSrc = uploadPathSrc.resolve(finalFileName);
                        Path filePathTarget = uploadPathTarget.resolve(finalFileName);

                        BufferedImage newBufferedImage = new BufferedImage(image.getWidth(),
                                image.getHeight(), BufferedImage.TYPE_INT_RGB);
                        newBufferedImage.createGraphics().drawImage(image, 0, 0, java.awt.Color.WHITE, null);
                        
                        ImageIO.write(newBufferedImage, "jpg", filePathSrc.toFile());
                        ImageIO.write(newBufferedImage, "jpg", filePathTarget.toFile());
                        
                        oldRecipe.setImage(finalFileName);
                    } else {
                        // Fallback
                        String originalExt = "unknown";
                        String originalName = file.getOriginalFilename();
                        if (originalName != null && originalName.lastIndexOf(".") != -1) {
                            originalExt = originalName.substring(originalName.lastIndexOf(".") + 1);
                        }

                        finalFileName = baseFileName + "-" + timestamp + "." + originalExt;
                        Path filePathSrc = uploadPathSrc.resolve(finalFileName);
                        Path filePathTarget = uploadPathTarget.resolve(finalFileName);
                        
                        Files.copy(file.getInputStream(), filePathSrc);
                        // Per il secondo copy, dobbiamo resettare lo stream o riaprirlo?
                        // InputStream non è riutilizzabile. Meglio copiare da src a target.
                        Files.copy(filePathSrc, filePathTarget);
                        
                        oldRecipe.setImage(finalFileName);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            this.recipeService.saveRecipe(oldRecipe);
            return "redirect:/recipe/" + oldRecipe.getId();
        }
    }
}
