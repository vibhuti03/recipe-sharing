package com.vibhuti.recipeSharing.controller;

import com.vibhuti.recipeSharing.utils.FileUploadUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class RecipeController {

    private final String uploadDir = "/Volumes/SanDisk1/Learnings/recipes/";

    @PostMapping("recipe-intake")
    public ResponseEntity<Object> recipeIntake(@RequestParam("recipe") MultipartFile recipe) throws IOException {
        String fileName = System.currentTimeMillis() + recipe.getOriginalFilename();
        FileUploadUtils.saveFile(uploadDir, fileName, recipe);

        return ResponseEntity.accepted().body("Recipe Uploaded");
    }
}
