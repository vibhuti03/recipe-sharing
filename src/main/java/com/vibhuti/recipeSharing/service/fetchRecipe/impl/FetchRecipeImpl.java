package com.vibhuti.recipeSharing.service.fetchRecipe.impl;

import com.vibhuti.recipeSharing.entity.RecipeEntity;
import com.vibhuti.recipeSharing.repositories.RecipeRepository;
import com.vibhuti.recipeSharing.service.fetchRecipe.FetchRecipe;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FetchRecipeImpl implements FetchRecipe {

    @Autowired
    private final RecipeRepository recipeRepository;
    @Override
    public List<String> fetchAllRecipe() {

        List<RecipeEntity> recipeEntityList = recipeRepository.findAll();
        return recipeEntityList.stream()
                .map(RecipeEntity::getRecipeName)
                .collect(Collectors.toList());

    }

    @Override
    public Resource fetchRecipeByName(String recipeName) throws Exception{
        RecipeEntity recipe = recipeRepository.findByRecipeName(recipeName);
        Path recipeFilePath = Path.of(recipe.getRecipeFileLocation());
        try {
            Resource resource = new UrlResource(recipeFilePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File" + recipeFilePath + "not found");
            }
        } catch (MalformedURLException malformedURLException){
            throw new FileNotFoundException("File" + recipeFilePath + "not found");
        }
    }
}
