package com.vibhuti.recipeSharing.service.recipeUpload;

import com.vibhuti.recipeSharing.entity.RecipeEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RecipeUpload {

    public RecipeEntity saveRecipe(String recipeName, List<String> recipeTags, MultipartFile recipeFile) throws IOException;
}
