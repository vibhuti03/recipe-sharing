package com.vibhuti.recipeSharing.service.fetchRecipe;

import java.util.List;

public interface FetchRecipe {
    public List<String> fetchAllRecipe();
    public String fetchRecipesByName(String name);
}
