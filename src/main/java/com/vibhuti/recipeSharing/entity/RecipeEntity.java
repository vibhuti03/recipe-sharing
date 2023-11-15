package com.vibhuti.recipeSharing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recipe")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipeName;

    @ManyToOne
    private RecipeTagsEntity recipeTags;

    private String recipeFileLocation;

}
