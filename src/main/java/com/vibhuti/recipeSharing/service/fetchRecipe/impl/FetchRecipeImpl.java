package com.vibhuti.recipeSharing.service.fetchRecipe.impl;

import com.vibhuti.recipeSharing.entity.RecipeEntity;
import com.vibhuti.recipeSharing.repositories.RecipeRepository;
import com.vibhuti.recipeSharing.service.fetchRecipe.FetchRecipe;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FetchRecipeImpl implements FetchRecipe {

    @Autowired
    private final RecipeRepository recipeRepository;
    @Override
    public List<String> fetchAllRecipe() {

        List<RecipeEntity> recipeEntityList = recipeRepository.findAll();

        List<String> allRecipeNames = new ArrayList<>();
        for(RecipeEntity recipe : recipeEntityList){
            allRecipeNames.add(recipe.getRecipeName());
        }

//        recipeEntityList.stream()
//                .map(r -> allRecipeNames.add(r.getRecipeName()));

        return allRecipeNames;
    }

    @Override
    public String fetchRecipesByName(String name) {
        return null;
    }
}
