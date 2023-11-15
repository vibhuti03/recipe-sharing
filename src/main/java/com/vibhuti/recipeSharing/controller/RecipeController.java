package com.vibhuti.recipeSharing.controller;

import com.vibhuti.recipeSharing.entity.RecipeEntity;
import com.vibhuti.recipeSharing.entity.RecipeTagsEntity;
import com.vibhuti.recipeSharing.repositories.RecipeRepository;
import com.vibhuti.recipeSharing.repositories.RecipeTagsRepository;
import com.vibhuti.recipeSharing.utils.FileUploadUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
    private final RecipeRepository recipeRepository;

    @Autowired
    private final RecipeTagsRepository recipeTagsRepository;
    private final String uploadDir = "/Volumes/SanDisk1/Learnings/recipes/";

    @PostMapping("recipe-intake")
    public ResponseEntity<Object> recipeIntake(@RequestParam("recipeName") String recipeName,
//                                               @RequestParam("recipeTags") String recipeTags,
                                               @RequestParam("recipe") MultipartFile recipeFile) throws IOException {
        String fileName = System.currentTimeMillis() + recipeFile.getOriginalFilename();
        FileUploadUtils.saveFile(uploadDir, fileName, recipeFile);

        RecipeEntity recipe = recipeRepository.save(RecipeEntity.builder()
                        .recipeName(recipeName)
//                        .recipeTags(recipeTagsRepository.save(RecipeTagsEntity.builder()
//                                .tagName(recipeTags)
//                                .build()))
                        .recipeFileLocation(uploadDir + fileName)
                .build());


        if(recipe!=null){
            return ResponseEntity.accepted().body("Recipe Uploaded");
        }
        return ResponseEntity.internalServerError().build();

    }
}
