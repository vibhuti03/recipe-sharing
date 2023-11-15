package com.vibhuti.recipeSharing.repositories;

import com.vibhuti.recipeSharing.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
}
