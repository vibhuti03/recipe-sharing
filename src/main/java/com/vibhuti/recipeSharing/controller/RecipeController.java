package com.vibhuti.recipeSharing.controller;

import com.vibhuti.recipeSharing.entity.RecipeEntity;
import com.vibhuti.recipeSharing.service.fetchRecipe.FetchRecipe;
import com.vibhuti.recipeSharing.service.recipeUpload.RecipeUpload;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class RecipeController {
    @Autowired
    private final RecipeUpload recipeUpload;

    @Autowired
    private final FetchRecipe fetchRecipe;
    @PostMapping("recipe-intake")
    public ResponseEntity<Object> recipeIntake(@RequestParam("recipeName") String recipeName,
                                               @RequestParam("recipeTags") List<String> recipeTags,
                                               @RequestParam("recipe") MultipartFile recipeFile) throws IOException {

        RecipeEntity savedRecipe = recipeUpload.saveRecipe(recipeName, recipeTags, recipeFile);
        if(savedRecipe!=null){
            return ResponseEntity.accepted().body("Recipe Uploaded");
        }
        return ResponseEntity.internalServerError().build();

    }

    @GetMapping("available-recipes")
    public ResponseEntity<List<String>> availableRecipes(){
        List<String> recipeNames = fetchRecipe.fetchAllRecipe();
        return ResponseEntity.ok().body(recipeNames);
    }

    @GetMapping("request-recipe/tag/{recipeTag}")
    public ResponseEntity<List<String>> recipesByTag(@PathVariable String recipeTag){
        List<String> recipeNames = fetchRecipe.fetchRecipesByTag(recipeTag);
        return ResponseEntity.ok().body(recipeNames);
    }

    @GetMapping("/recipe/{recipeName}")
    public ResponseEntity<Resource> recipeFileByName(@PathVariable String recipeName, HttpServletRequest request) throws Exception {
        Resource resource =  fetchRecipe.fetchRecipeByName(recipeName);
        String contentType = null;
        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex){
            throw new IOException("IOException encountered");
        }
        if (contentType == null){
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
