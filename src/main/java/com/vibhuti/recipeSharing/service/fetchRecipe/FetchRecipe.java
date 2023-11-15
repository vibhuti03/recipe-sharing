package com.vibhuti.recipeSharing.service.fetchRecipe;

import org.springframework.core.io.Resource;
import java.util.List;

public interface FetchRecipe {
    public List<String> fetchAllRecipe();
    public Resource fetchRecipeByName(String name) throws Exception;
}
