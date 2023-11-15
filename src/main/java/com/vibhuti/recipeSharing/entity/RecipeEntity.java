package com.vibhuti.recipeSharing.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "recipe")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId;

    private String recipeName;

    @OneToMany(mappedBy = "recipe")
    private Set<RecipeTagsEntity> recipeTags;

    private String recipeFileLocation;

}
