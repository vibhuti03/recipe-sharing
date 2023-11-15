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
                .map(r -> r.getRecipeName())
                .collect(Collectors.toList());

    }

    @Override
    public String fetchRecipesByName(String name) {
        return null;
    }
}
