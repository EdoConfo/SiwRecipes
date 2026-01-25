package it.uniroma3.siw_recipes.controller;

import it.uniroma3.siw_recipes.model.Recipe;
import it.uniroma3.siw_recipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ImageController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/images/uploads/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) {
        // Cerchiamo la ricetta che ha questo nome file
        // Nota: Questo approccio richiede di avere un metodo nel repository per cercare per 'image'
        // Oppure possiamo assumere che il servizio possa darci l'immagine.
        // Dato che non abbiamo un findByImage facile, proviamo a costruire un URL "smart" o
        // assumiamo che per ora serva solo per servire blob se il file locale non c'è.
        
        // In realtà, se siamo in un ambiente Cloud puro (senza disco effimero persistente),
        // dovremmo servire SEMPRE dal DB.
        
        // Poiché i template HTML puntano a src="/images/uploads/NomeFile.jpg",
        // Spring cercherà prima nelle risorse statiche. Se non trova nulla, arriverà qui?
        // NO, le risorse statiche vincono.
        
        // Tuttavia, se siamo su Heroku/Supabase senza volume, le risorse statiche caricate a runtime
        // NON esistono sul disco, quindi Spring MVC gestirà la richiesta se mappiamo questo URL.
        
        // Strategia:
        // 1. Cerchiamo nel DB una ricetta che abbia questo 'filename' nel campo image.
        Recipe recipe = recipeService.getRecipeByImageFilename(filename);
        
        if (recipe != null && recipe.getImageData() != null) {
            HttpHeaders headers = new HttpHeaders();
            
            // Determina il content type (semplificato a jpeg/png basato su estensione)
            if (filename.toLowerCase().endsWith(".png")) {
                headers.setContentType(MediaType.IMAGE_PNG);
            } else if (filename.toLowerCase().endsWith(".avif")) {
                 headers.set("Content-Type", "image/avif");
            } else {
                headers.setContentType(MediaType.IMAGE_JPEG);
            }
            
            headers.setContentLength(recipe.getImageData().length);
            
            return new ResponseEntity<>(recipe.getImageData(), headers, HttpStatus.OK);
        }
        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
