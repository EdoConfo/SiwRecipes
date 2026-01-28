package it.uniroma3.siw_recipes.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import it.uniroma3.siw_recipes.model.Ingredient;
import it.uniroma3.siw_recipes.model.Recipe;
import it.uniroma3.siw_recipes.model.Review;
import it.uniroma3.siw_recipes.model.User;
import it.uniroma3.siw_recipes.service.CredentialsService;
import it.uniroma3.siw_recipes.service.RecipeService;
import it.uniroma3.siw_recipes.service.ReviewService;
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

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/recipes/new")
    public String formNewRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("currentUser", this.getCurrentUser());
        return "formNewRecipe";
    }

    @PostMapping("/recipes")
    public String newRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult, 
            @RequestParam("file") MultipartFile file, 
            @RequestParam(value = "ingName", required = false) List<String> ingNames,
            @RequestParam(value = "ingQuantity", required = false) List<String> ingQuantities,
            @RequestParam(value = "ingUnit", required = false) List<String> ingUnits,
            Model model) {
        
        // Validazione Immagine: Obbligatoria per nuove ricette
        if (file.isEmpty()) {
            bindingResult.rejectValue("image", "required", "L'immagine è obbligatoria");
        }

        // Validazione Ingredienti: Se c'è il nome, deve esserci la quantità
        boolean atLeastOneIngredient = false;
        if (ingNames != null) {
            for (int i = 0; i < ingNames.size(); i++) {
                String name = ingNames.get(i);
                if (name != null && !name.trim().isEmpty()) {
                    atLeastOneIngredient = true;
                    String qty = (ingQuantities != null && i < ingQuantities.size()) ? ingQuantities.get(i) : "";
                    if (qty == null || qty.trim().isEmpty()) {
                        bindingResult.reject("ingredients.quantity", "La quantità è obbligatoria per l'ingrediente: " + name);
                    }
                }
            }
        }
        
        if (!atLeastOneIngredient) {
            bindingResult.reject("ingredients.empty", "Inserisci almeno un ingrediente");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("currentUser", this.getCurrentUser());
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
                        
                        // Save bytes to DB for hybrid/cloud support (Added by Copilot)
                        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
                        ImageIO.write(newBufferedImage, "jpg", baos);
                        recipe.setImageData(baos.toByteArray());

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
                        
                        // Save bytes to DB for hybrid/cloud support
                        recipe.setImageData(file.getBytes());

                        recipe.setImage(finalFileName);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Gestione Ingredienti
            if (ingNames != null && !ingNames.isEmpty()) {
                List<Ingredient> ingredients = new ArrayList<>();
                for (int i = 0; i < ingNames.size(); i++) {
                    String name = ingNames.get(i);
                    if (name != null && !name.trim().isEmpty()) {
                        String qty = (ingQuantities != null && i < ingQuantities.size()) ? ingQuantities.get(i) : "";
                        String unit = (ingUnits != null && i < ingUnits.size()) ? ingUnits.get(i) : "";
                        
                        Ingredient ingredient = new Ingredient();
                        ingredient.setName(name);
                        ingredient.setQuantity(qty);
                        ingredient.setUnitOfMeasure(unit);
                        ingredient.setRecipe(recipe);
                        ingredients.add(ingredient);
                    }
                }
                recipe.setIngredients(ingredients);
            }

            this.recipeService.saveRecipe(recipe);
            return "redirect:/recipe/" + recipe.getId();
        }
    }

    /**
     * Gestisce la richiesta per visualizzare l'elenco di tutte le ricette.
     * URL: /recipes
     * Supporta filtro opzionale per parola chiave.
     * 
     * @param keyword Parola chiave per la ricerca (opzionale).
     * @param model Il "contenitore" dove mettiamo i dati da inviare alla pagina HTML.
     * @return Il nome del file HTML da visualizzare (senza .html).
     */
    @GetMapping("/recipes")
    public String getRecipes(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        // Se c'è una parola chiave, cerchiamo (filtered). Altrimenti mostriamo tutto.
        List<it.uniroma3.siw_recipes.model.RecipeSummary> recipes;
        if (keyword != null && !keyword.trim().isEmpty()) {
            recipes = this.recipeService.findSummaryByTitle(keyword.trim());
            model.addAttribute("keyword", keyword.trim());
        } else {
            recipes = this.recipeService.getRecipesSummaryOrderedByTitle();
        }
        model.addAttribute("recipes", recipes);

        // Calcolo media recensioni per tutte le ricette in una sola query
        Map<Long, String> averageRatings = this.reviewService.getAverageRatingsForAllRecipes();
        model.addAttribute("averageRatings", averageRatings);

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
    public String getRecipe(@PathVariable("id") Long id, @RequestParam(required = false) Long editingReviewId, Model model) {
        // Cerchiamo la ricetta per ID
        Recipe recipe = this.recipeService.getRecipe(id);

        if (recipe != null) {
            // Carichiamo esplicitamente le recensioni per evitare LazyInitializationException
            List<Review> reviews = this.reviewService.getReviewsByRecipe(recipe);
            recipe.setReviews(reviews);

            // Calcolo media delle valutazioni
            double averageRating = 0.0;
            if (reviews != null && !reviews.isEmpty()) {
                double sum = 0.0;
                for (Review r : reviews) {
                    if (r.getRating() != null) sum += r.getRating();
                }
                averageRating = sum / reviews.size();
            }
            model.addAttribute("averageRating", String.format("%.1f", averageRating));
            

            // Se esiste, la mettiamo nel modello
            model.addAttribute("recipe", recipe);
            model.addAttribute("editingReviewId", editingReviewId);

            // Se siamo in modalità modifica, carichiamo la recensione nel model per il form
            if (editingReviewId != null) {
                Review reviewToEdit = this.reviewService.getReview(editingReviewId);
                if (reviewToEdit != null) {
                    model.addAttribute("review", reviewToEdit);
                }
            }

            // Gestione permessi e ruoli
            boolean isAuthor = false;
            boolean isAdmin = false;

            // Aggiungiamo l'utente corrente se loggato
            User currentUser = this.getCurrentUser();
            if (currentUser != null) {
                model.addAttribute("currentUser", currentUser);

                Credentials credentials = this.getCurrentCredentials();
                if (credentials != null && credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
                    isAdmin = true;
                }

                // Verifica se l'utente è l'autore
                if (recipe.getAuthor() != null && recipe.getAuthor().equals(currentUser)) {
                    isAuthor = true;
                }

                // Se NON è l'autore, prepara l'oggetto per la recensione
                if (!isAuthor) {
                    model.addAttribute("newReview", new Review());
                }
            }

            model.addAttribute("isAuthor", isAuthor);
            model.addAttribute("isAdmin", isAdmin);

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
            Credentials credentials = this.getCurrentCredentials();
            if (credentials != null && (credentials.getRole().equals(Credentials.ADMIN_ROLE) || 
                (credentials.getUser() != null && recipe.getAuthor().getId().equals(credentials.getUser().getId())))) {
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
                model.addAttribute("currentUser", credentials.getUser());
                return "formEditRecipe";
            }
        }
        return "redirect:/recipes";
    }

    @PostMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable("id") Long id, @Valid @ModelAttribute("recipe") Recipe recipe, 
            BindingResult bindingResult, @RequestParam("file") MultipartFile file,
            @RequestParam(value = "ingId", required = false) List<String> ingIds,
            @RequestParam(value = "ingName", required = false) List<String> ingNames,
            @RequestParam(value = "ingQuantity", required = false) List<String> ingQuantities,
            @RequestParam(value = "ingUnit", required = false) List<String> ingUnits,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentUser", this.getCurrentUser());
            return "formEditRecipe";
        } else {
            Recipe oldRecipe = this.recipeService.getRecipe(id);
            if (oldRecipe == null) {
                return "redirect:/recipes";
            }
            
            // Aggiorniamo i campi
            oldRecipe.setTitle(recipe.getTitle());
            oldRecipe.setCategory(recipe.getCategory());
            oldRecipe.setDescription(recipe.getDescription());
            oldRecipe.setPrepTime(recipe.getPrepTime());
            oldRecipe.setDifficulty(recipe.getDifficulty());
            oldRecipe.setProcedure(recipe.getProcedure());

            // Aggiorniamo gli ingredienti
            if (ingNames != null) {
                // Mappa degli ingredienti esistenti per ID
                Map<Long, Ingredient> existingIngredients = new HashMap<>();
                for (Ingredient i : oldRecipe.getIngredients()) {
                    existingIngredients.put(i.getId(), i);
                }
                
                List<Ingredient> updatedIngredients = new ArrayList<>();
                
                for (int i = 0; i < ingNames.size(); i++) {
                    String name = ingNames.get(i);
                    if (name != null && !name.trim().isEmpty()) {
                        String idStr = (ingIds != null && i < ingIds.size()) ? ingIds.get(i) : null;
                        Ingredient ing = null;
                        
                        if (idStr != null && !idStr.isEmpty()) {
                            try {
                                Long ingId = Long.parseLong(idStr);
                                ing = existingIngredients.get(ingId);
                            } catch (NumberFormatException e) {}
                        }
                        
                        if (ing == null) {
                            ing = new Ingredient();
                            ing.setRecipe(oldRecipe);
                        }
                        
                        ing.setName(name);
                        ing.setQuantity((ingQuantities != null && i < ingQuantities.size()) ? ingQuantities.get(i) : "");
                        ing.setUnitOfMeasure((ingUnits != null && i < ingUnits.size()) ? ingUnits.get(i) : "");
                        
                        updatedIngredients.add(ing);
                    }
                }
                
                oldRecipe.getIngredients().clear();
                oldRecipe.getIngredients().addAll(updatedIngredients);
            } else {
                oldRecipe.getIngredients().clear();
            }
            
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
                        
                        // Save bytes to DB for hybrid/cloud support
                        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
                        ImageIO.write(newBufferedImage, "jpg", baos);
                        oldRecipe.setImageData(baos.toByteArray());

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
                        
                        // Save bytes to DB for hybrid/cloud support
                        oldRecipe.setImageData(file.getBytes());

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

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
            if (credentials != null) {
                return credentials.getUser();
            }
        }
        return null;
    }

    private Credentials getCurrentCredentials() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            return credentialsService.getCredentials(userDetails.getUsername());
        }
        return null;
    }

    //mapping per visualizzare le ricette di un autore specifico
    @GetMapping("/recipes/author/{authorId}")
    public String getRecipesByAuthor(@PathVariable("authorId") Long authorId, Model model) {
        List<Recipe> recipes = this.recipeService.findSummaryByAuthorId(authorId);
        model.addAttribute("recipes", recipes);

        // Calcolo media recensioni per tutte le ricette in una sola query
        Map<Long, String> averageRatings = this.reviewService.getAverageRatingsForAllRecipes();
        model.addAttribute("averageRatings", averageRatings);

        return "recipes";
    }

    //mapping per visualizzare le ricette di una categoria specifica
    @GetMapping("/recipes/category/{category}")
    public String getRecipesByCategory(@PathVariable("category") String category, Model model) {
        List<Recipe> recipes = this.recipeService.findByCategory(category);
        model.addAttribute("recipes", recipes);

        // Calcolo media recensioni per tutte le ricette in una sola query
        Map<Long, String> averageRatings = this.reviewService.getAverageRatingsForAllRecipes();
        model.addAttribute("averageRatings", averageRatings);

        return "recipes";
    }

    //mapping per visualizzare ricette caricate in una certa data (che in Recipe.java è una LocalDateTime)
    @GetMapping("/recipes/date/{date}")
    public String getRecipesByDate(@PathVariable("date") String date, Model model) {
        List<Recipe> recipes = this.recipeService.findByCreationDate(date);
        model.addAttribute("recipes", recipes);

        // Calcolo media recensioni per tutte le ricette in una sola query
        Map<Long, String> averageRatings = this.reviewService.getAverageRatingsForAllRecipes();
        model.addAttribute("averageRatings", averageRatings);

        return "recipes";
    }

    //mapping per visualizzare le ricette con quell'esatto tempo di preparazione
    @GetMapping("/recipes/preptime/{prepTime}")
    public String getRecipesByPrepTime(@PathVariable("prepTime") Integer prepTime,    Model model) {
        List<Recipe> recipes = this.recipeService.findByPrepTime(prepTime);
        model.addAttribute("recipes", recipes); 
        // Calcolo media recensioni per tutte le ricette in una sola query
        Map<Long, String> averageRatings = this.reviewService.getAverageRatingsForAllRecipes();
        model.addAttribute("averageRatings", averageRatings);        
        return "recipes";
    }

    //mapping per visualizzare le ricette con una certa difficoltà
    @GetMapping("/recipes/difficulty/{difficulty}")
    public String getRecipesByDifficulty(@PathVariable("difficulty") Integer difficulty, Model model) {
        List<Recipe> recipes = this.recipeService.getRecipesByDifficulty(difficulty);
        model.addAttribute("recipes", recipes);

        // Calcolo media recensioni per tutte le ricette in una sola query
        Map<Long, String> averageRatings = this.reviewService.getAverageRatingsForAllRecipes();
        model.addAttribute("averageRatings", averageRatings);

        return "recipes";
    }

    //mappinng per visualizzare le ricette con una certa valutazione media (la media estratta non è sul DB ma calcolata sulle recensioni)
    @GetMapping("/recipes/rating/{rating}")
    public String getRecipesByRating(@PathVariable("rating") String rating, Model model) {
        List<Recipe> recipes = this.recipeService.getRecipesByAverageRating(rating);
        model.addAttribute("recipes", recipes);
        // Calcolo media recensioni per tutte le ricette in una sola query
        Map<Long, String> averageRatings = this.reviewService.getAverageRatingsForAllRecipes();
        model.addAttribute("averageRatings", averageRatings);
        return "recipes";
    }
}

