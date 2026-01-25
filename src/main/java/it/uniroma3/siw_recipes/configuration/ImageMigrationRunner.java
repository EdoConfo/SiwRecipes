package it.uniroma3.siw_recipes.configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.uniroma3.siw_recipes.model.Recipe;
import it.uniroma3.siw_recipes.repository.RecipeRepository;

@Component
public class ImageMigrationRunner implements CommandLineRunner {

    private final RecipeRepository recipeRepository;

    public ImageMigrationRunner(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // System.out.println("=== IMAGE MIGRATION STARTED ===");
        
        // Find only recipes that need migration
        List<Recipe> recipesToMigrate = recipeRepository.findByImageDataIsNullAndImageIsNotNull();
        
        if (recipesToMigrate.isEmpty()) {
            return;
        }

        System.out.println("=== MIGRATION NEEDED: Found " + recipesToMigrate.size() + " recipes without binary data ===");
        
        int updatedCount = 0;
        
        for (Recipe recipe : recipesToMigrate) {
            try {
                // Primary path: Source code structure (development)
                File file = new File("src/main/resources/static/images/uploads/" + recipe.getImage());
                
                if (!file.exists()) {
                    // Secondary path: Target structure (runtime/jar)
                   file = new File("target/classes/static/images/uploads/" + recipe.getImage());
                }

                if (file.exists()) {
                    byte[] fileContent = Files.readAllBytes(file.toPath());
                    recipe.setImageData(fileContent);
                    recipeRepository.save(recipe);
                    updatedCount++;
                }
            } catch (IOException e) {
                // Silent catch or simple log to avoid spam
            }
        }
        
        if (updatedCount > 0) {
            System.out.println("=== MIGRATION COMPLETED: Updated " + updatedCount + " recipes ===");
        }
    }
}
