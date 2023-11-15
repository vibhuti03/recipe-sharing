package com.vibhuti.recipeSharing.repositories;

import com.vibhuti.recipeSharing.entity.TagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeTagsRepository extends JpaRepository<TagsEntity, Long> {

    Optional<TagsEntity> findByTagName(String tagName);
}
