package com.vibhuti.recipeSharing.repositories;

import com.vibhuti.recipeSharing.entity.TagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeTagsRepository extends JpaRepository<TagsEntity, Long> {
}
