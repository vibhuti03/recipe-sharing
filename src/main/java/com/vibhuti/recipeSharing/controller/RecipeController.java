package com.vibhuti.recipeSharing.controller;

import com.vibhuti.recipeSharing.entity.RecipeEntity;
import com.vibhuti.recipeSharing.service.recipeUpload.RecipeUpload;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class RecipeController {
    @Autowired
    private final RecipeUpload recipeUpload;
    @PostMapping("recipe-intake")
    public ResponseEntity<Object> recipeIntake(
//            @RequestParam RecipeEntity recipe,
                                                @RequestParam("recipeName") String recipeName,
                                               @RequestParam("recipeTags") List<String> recipeTags,
                                               @RequestParam("recipe") MultipartFile recipeFile) throws IOException {

        RecipeEntity savedRecipe = recipeUpload.saveRecipe(recipeName, recipeTags, recipeFile);
        if(savedRecipe!=null){
            return ResponseEntity.accepted().body("Recipe Uploaded");
        }
        return ResponseEntity.internalServerError().build();

    }
}
