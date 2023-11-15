package com.vibhuti.recipeSharing.repositories;

import com.vibhuti.recipeSharing.entity.RecipeTagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeTagsRepository extends JpaRepository<RecipeTagsEntity, Long> {
}
