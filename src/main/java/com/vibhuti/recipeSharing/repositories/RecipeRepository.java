package com.vibhuti.recipeSharing.repositories;

import com.vibhuti.recipeSharing.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    RecipeEntity findByRecipeName(String recipeName);

    @Query(value = "select * from recipe r inner join recipe_tags rt on r.id = rt.recipes_id where rt.tags_id = ?1",
            nativeQuery = true)
    List<RecipeEntity> findByTagId(Long tagId);
}
