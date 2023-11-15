package com.vibhuti.recipeSharing.service.recipeUpload.impl;

import com.vibhuti.recipeSharing.entity.RecipeEntity;
import com.vibhuti.recipeSharing.entity.TagsEntity;
import com.vibhuti.recipeSharing.repositories.RecipeRepository;
import com.vibhuti.recipeSharing.repositories.TagsRepository;
import com.vibhuti.recipeSharing.service.recipeUpload.RecipeUpload;
import com.vibhuti.recipeSharing.utils.FileUploadUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RecipeUploadImpl implements RecipeUpload {

    private final String uploadDir = "/Volumes/SanDisk1/Learnings/recipes/";

    @Autowired
    private final RecipeRepository recipeRepository;

    @Autowired
    private final TagsRepository tagsRepository;

    @Override
    public RecipeEntity saveRecipe(String recipeName, List<String> recipeTags, MultipartFile recipeFile) throws IOException {
        String fileName = System.currentTimeMillis() + recipeFile.getOriginalFilename();
        FileUploadUtils.saveFile(uploadDir, fileName, recipeFile);

        Set<TagsEntity> recipeTagsEntitySet = new HashSet<>();

        for (String tag : recipeTags) {
            Optional<TagsEntity> tagPresent = tagsRepository.findByTagName(tag);
            if (tagPresent.isPresent()) {
                recipeTagsEntitySet.add(tagPresent.get());
            } else {
                TagsEntity recipeTagsEntity = tagsRepository.save(TagsEntity.builder()
                        .tagName(tag)
                        .build());
                recipeTagsEntitySet.add(recipeTagsEntity);
            }
        }

        RecipeEntity recipe = RecipeEntity.builder()
                .recipeName(recipeName)
                .recipeFileLocation(uploadDir + fileName)
                .tags(recipeTagsEntitySet)
                .build();

        RecipeEntity savedRecipe = recipeRepository.save(recipe);

        return savedRecipe;
    }
}
